package com.knd.common.bean;

/**
 * 动态页面楼层元素
 */
public class ElementBean extends DynamicBean{

    /**
     * 元素名称
     */
    private String elementName;
    /**
     * 元素详情
     */
    private String elementDetail;
    /**
     * 元素备注
     */
    private String elementNote;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementDetail() {
        return elementDetail;
    }

    public void setElementDetail(String elementDetail) {
        this.elementDetail = elementDetail;
    }

    public String getElementNote() {
        return elementNote;
    }

    public void setElementNote(String elementNote) {
        this.elementNote = elementNote;
    }


    public void setElement(ElementBean bean){
        if(bean == null){
            return;
        }
        elementName = bean.getElementName();
        elementDetail = bean.getElementDetail();
        elementNote = bean.getElementNote();
        setDynamicBean(bean);
    }
}
