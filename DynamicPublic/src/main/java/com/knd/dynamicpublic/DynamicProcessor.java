package com.knd.dynamicpublic;

import android.net.Uri;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.google.auto.service.AutoService;
import com.knd.dynamicannotations.Comments;
import com.knd.dynamicannotations.ParamKeys;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
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

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtil = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(ParamKeys.class.getCanonicalName());
        annotataions.add(Comments.class.getCanonicalName());
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
            paresParam(roundEnv.getElementsAnnotatedWith(ParamKeys.class));
            return true;
        }
        return false;
    }

    private void paresParam(Set<? extends Element> routeElements) {

        //TypeSpec 生成类，接口，或者枚举   生成BindMainActivity类
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder("Param")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Serializable.class);

        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("getMap")
                .addModifiers(Modifier.PUBLIC)
                .returns(Map.class);
        CodeBlock.Builder blockBuilderIoc = CodeBlock.builder();//创建一段代码块

        MethodSpec.Builder initSpecBuilder = MethodSpec.methodBuilder("initFromUrl")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class,"url")
                .returns(TypeName.VOID);
        CodeBlock.Builder initBuilderIoc = CodeBlock.builder();//创建一段代码块
        initBuilderIoc.addStatement("$T uri = Uri.parse(url)", Uri.class);
        initBuilderIoc.beginControlFlow("if(uri != null)");

        for(Element page : routeElements){
            TypeName pageName = ClassName.get(page.asType());

            blockBuilderIoc.addStatement("$T map = new $T()",Map.class, ArrayMap.class);
            for(Element element : page.getEnclosedElements()){
                if(element instanceof VariableElement){
                    Comments comments = element.getAnnotation(Comments.class);

                    String key = element.getSimpleName().toString();
                    String value = ((VariableElement) element).getConstantValue().toString();
                    FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class,value)
                            .addModifiers(Modifier.PUBLIC);
                    if(comments != null){
                        if(!comments.isString()){
                            fieldSpec = FieldSpec.builder(Object.class,value)
                                    .addModifiers(Modifier.PUBLIC);
                        }
                        if(comments.value() != null){
                            fieldSpec.addJavadoc(comments.value());
                        }
                    }
                    typeSpec.addField(fieldSpec.build());
                    blockBuilderIoc.beginControlFlow("if($L != null)",value);
                    blockBuilderIoc.addStatement("map.put($T.$L,$L)",pageName,key,value);
                    blockBuilderIoc.endControlFlow();

                    if(comments.isString()){
                        String data = value+"$$Data";
                        initBuilderIoc.addStatement("String $L = uri.getQueryParameter($T.$L)",data,pageName,key);
                        initBuilderIoc.beginControlFlow("if(!$T.isEmpty($L))", TextUtils.class,data);
                        initBuilderIoc.addStatement("$L = $L",value,data);
                        initBuilderIoc.endControlFlow();
                    }
                }
            }
        }

        blockBuilderIoc.addStatement("return map");
        methodSpecBuilder.addCode(blockBuilderIoc.build());
        typeSpec.addMethod(methodSpecBuilder.build());

        initBuilderIoc.endControlFlow();
        initSpecBuilder.addCode(initBuilderIoc.build());
        typeSpec.addMethod(initSpecBuilder.build());

        //用于构造输出包含一个顶级类的Java文件
        JavaFile file = JavaFile.builder("com.knd.common.route",typeSpec.build()).build();
        try {
            file.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
