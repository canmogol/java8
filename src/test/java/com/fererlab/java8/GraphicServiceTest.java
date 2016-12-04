package com.fererlab.java8;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

public class GraphicServiceTest {

    private GraphicServiceImpl graphicService;
    private Panel root;
    private static Panel included_one;
    private static Panel included_two;
    private static Panel included_three;
    private static Panel excluded_four;
    private final static int INITIAL_COUNT = 3;
    private final static int FINAL_COUNT = INITIAL_COUNT + 1;

    @BeforeClass
    public static void prepare() {
        // prepare root panel
        included_one = new Panel("included_one", 1, 10);
        included_two = new Panel("included_two", 2, 20);
        included_three = new Panel("included_three", 3, 30);
        excluded_four = new Panel("excluded_four", 4, 40);
    }

    @Before
    public void everyMethod() {
        // create service object, this should be stateless
        graphicService = new GraphicServiceImpl();
        // create new root element for each method
        root = new Panel("root", 100, 100);
        root.addChild(included_one);
        root.addChild(included_two);
        root.addChild(included_three);
    }

    @Test
    public void GraphicService_addPanel() {
        Assert.assertEquals(root.getChildren().size(), INITIAL_COUNT);
        graphicService.addPanel(root, excluded_four);
        Assert.assertEquals(root.getChildren().size(), FINAL_COUNT);
    }

    @Test
    public void GraphicService_getPanels() {
        Assert.assertEquals(root.getChildren().size(), INITIAL_COUNT);
        Collection<Panel> children = graphicService.getPanels(root);
        Assert.assertEquals(children.size(), INITIAL_COUNT);
        for (Panel panel : children) {
            Assert.assertTrue(root.getChildren().contains(panel));
        }
    }

    @Test
    public void GraphicService_removePanel() {
        Assert.assertEquals(root.getChildren().size(), INITIAL_COUNT);
        Panel removed = graphicService.removePanel(root, included_one.getTitle());
        Assert.assertEquals(removed, included_one);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void GraphicService_removePanelFromGetPanels() {
        Assert.assertEquals(root.getChildren().size(), INITIAL_COUNT);
        Collection<Panel> children = graphicService.getPanels(root);
        Iterator<Panel> iterator = children.iterator();
        iterator.remove();
    }

}
