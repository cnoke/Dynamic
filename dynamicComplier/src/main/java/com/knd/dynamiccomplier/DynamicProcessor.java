package com.knd.dynamiccomplier;

import android.net.Uri;

import com.google.auto.service.AutoService;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicannotations.Key;
import com.knd.dynamicannotations.Model;
import com.knd.dynamicannotations.Url;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

/**
 * @date on 2021/3/1
 * @author huanghui
 * @title 事件注解扫描器
 * @describe
 */
@AutoService(Processor.class)
public class DynamicProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtil;
    private int eIndex;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtil = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(Adapter.class.getCanonicalName());
        annotataions.add(Model.class.getCanonicalName());
        annotataions.add(Key.class.getCanonicalName());
        annotataions.add(Url.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        if(set != null && !set.isEmpty()) {
            //获取所有Route注解元素
            parseRoutes(roundEnv.getElementsAnnotatedWith(Adapter.class));
            paresNetWork(roundEnv.getElementsAnnotatedWith(Model.class));
            return true;
        }
        return false;
    }

    private void parseRoutes(Set<? extends Element> routeElements) {

        ClassName aClass = ClassName.get("com.knd.dynamicpage.adapter.base", "BaseDelegateAdapter");

        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("initDynamic")
                .addModifiers(Modifier.PUBLIC,Modifier.STATIC)//public static
                .returns(aClass)
                .addParameter(String.class, "key");//返回值  void

        MethodSpec.Builder getIndexMethod = MethodSpec.methodBuilder("getIndex")
                .addModifiers(Modifier.PUBLIC,Modifier.STATIC)//public static
                .returns(TypeName.INT)
                .addParameter(String.class,"key");//返回值  void

        String packageName = "";
        CodeBlock.Builder blockBuilderIoc = CodeBlock.builder();//创建一段代码块
        blockBuilderIoc.addStatement("$T base = null",aClass);
        blockBuilderIoc.beginControlFlow("switch (key)");

        CodeBlock.Builder getIndexIoc = CodeBlock.builder();//创建一段代码块
        getIndexIoc.addStatement("$T index = -1",TypeName.INT);
        getIndexIoc.beginControlFlow("switch (key)");

        int index = 0;
        for (Element element : routeElements) {
            if("".equals(packageName)){
                packageName = mElementUtil.getPackageOf(element).toString();
            }
            //类信息
            TypeMirror typeMirror = element.asType();
            Adapter route = element.getAnnotation(Adapter.class);

            blockBuilderIoc.add("case $S: \n", route.value());
            blockBuilderIoc.addStatement("base = new $T()",typeMirror);
            blockBuilderIoc.addStatement("break");

            getIndexIoc.add("case $S: \n", route.value());
            getIndexIoc.addStatement("index = " + index);
            getIndexIoc.addStatement("break");
            index++;
        }
        blockBuilderIoc.addStatement("default: break");
        blockBuilderIoc.endControlFlow();
        blockBuilderIoc.addStatement("return base");
        methodSpecBuilder.addCode(blockBuilderIoc.build());

        getIndexIoc.addStatement("default: break");
        getIndexIoc.endControlFlow();
        getIndexIoc.addStatement("return index");
        getIndexMethod.addCode(getIndexIoc.build());

        if(packageName == null || packageName.equals("")){
            return;
        }
        //TypeSpec 生成类，接口，或者枚举   生成BindMainActivity类
        TypeSpec typeSpec = TypeSpec.classBuilder("DynamicBind")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpecBuilder.build())//添加上面得方法
                .addMethod(getIndexMethod.build())
                .build();
        //用于构造输出包含一个顶级类的Java文件
        JavaFile file = JavaFile.builder(packageName,typeSpec).build();
        try {
            file.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void paresNetWork(Set<? extends Element> models) {
        String packageName = "";

        ClassName aClass = ClassName.get("com.knd.dynamicpage.adapter.base", "BaseDelegateAdapter");

        //TypeSpec 生成类，接口，或者枚举   生成BindMainActivity类
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder("DynamicNetWork")
                .addModifiers(Modifier.PUBLIC);//添加上面得方法;
        for(Element element : models){
            TypeElement model;
            if(element instanceof TypeElement){
                model =  (TypeElement)element;
            }else{
                continue;
            }
            if("".equals(packageName)){
                packageName = mElementUtil.getPackageOf(model).toString();
            }
            MethodSpec.Builder methodSpecBuilder = null;

            CodeBlock.Builder blockBuilderIoc = CodeBlock.builder();//创建一段代码块
            blockBuilderIoc.addStatement("$T result = false", TypeName.BOOLEAN);
            blockBuilderIoc.addStatement("$T uri = Uri.parse(url)", Uri.class);
            blockBuilderIoc.addStatement("$T key = uri.getPath()",String.class);

            TypeName typeName = ClassName.get(model.asType());
            methodSpecBuilder = MethodSpec.methodBuilder(model.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC,Modifier.STATIC)//public static
                    .returns(TypeName.BOOLEAN)
                    .addParameter(String.class, "url")
                    .addParameter(aClass, "adapter");
            methodSpecBuilder.addParameter(typeName,"model");
            blockBuilderIoc.beginControlFlow("switch (key)");
            eIndex = 0;

            Element subs = model.getEnclosingElement();
            if(subs != null && subs.getEnclosedElements() != null){
                for(Element sub : subs.getEnclosedElements()){
                    if(sub != null && sub instanceof TypeElement
                            && sub.getEnclosedElements() != null){
                        TypeName subeName = ClassName.get(sub.asType());
                        if(subeName.equals(typeName)
                                || sub.getSimpleName().toString().equals("DynamicBaseModel")){
                            for(Element subElement : sub.getEnclosedElements()){
                                setData(blockBuilderIoc,subElement,aClass);
                            }
                        }
                    }
                }
            }

            blockBuilderIoc.add("default: \n");
            blockBuilderIoc.addStatement("result = false");
            blockBuilderIoc.addStatement("break");
            blockBuilderIoc.endControlFlow();
            blockBuilderIoc.addStatement("return result");
            methodSpecBuilder.addCode(blockBuilderIoc.build());

            typeSpec.addMethod(methodSpecBuilder.build());
        }

        if(packageName == null || packageName.equals("")){
            return;
        }

        //用于构造输出包含一个顶级类的Java文件
        JavaFile file = JavaFile.builder(packageName,typeSpec.build()).build();
        try {
            file.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setData(CodeBlock.Builder blockBuilderIoc,Element element,ClassName aClass){

        if(!(element instanceof ExecutableElement)){
            return;
        }
        ExecutableElement executableElement = (ExecutableElement)element;
        Url url = executableElement.getAnnotation(Url.class);
        if(url == null){
            return;
        }

        blockBuilderIoc.add("case $S: \n", "/front/front/"+url.value());

        List<VariableElement> list = (List<VariableElement>)executableElement.getParameters();
        int index = 0;
        if(list != null && !list.isEmpty()){
            for(VariableElement variableElement : list){
                Key annotation = variableElement.getAnnotation(Key.class);
                if(annotation == null){
                    continue;
                }
                if("com.knd.dynamicpage.adapter.base.BaseDelegateAdapter".equals(variableElement.asType().toString())){
                    blockBuilderIoc.addStatement("$T parameter"+ eIndex +""+ index +" = adapter",aClass);
                }else{
                    blockBuilderIoc.addStatement("$T parameter"+ eIndex +""+ index +" = uri.getQueryParameter($S)",String.class,annotation.value());
                }
                index++;
            }
        }
        blockBuilderIoc.add("model.$L(",executableElement.getSimpleName());
        for(int i = 0 ; i < index ; i ++){
            blockBuilderIoc.add("parameter"+ eIndex +""+ i);
            if(i != index -1){
                blockBuilderIoc.add(",");
            }
        }
        blockBuilderIoc.add(");\n");
        blockBuilderIoc.addStatement("result = true");
        blockBuilderIoc.addStatement("break");
        eIndex++;
    }

}
