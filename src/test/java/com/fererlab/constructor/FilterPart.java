package com.fererlab.constructor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.soap.Text;
import java.awt.*;

public class FilterPart extends BaseClass {

    @Resource
    private ItemSelectable broker;

    private Label label;
    private Text text;
    private Button button;

    public FilterPart() {
        System.out.println("---- Filter Part");
    }

    @PostConstruct
    public void postConstruct(Composite parent) {
        broker.getSelectedObjects();
        label.addNotify();
        text.isComment();
        button.addNotify();
    }

}