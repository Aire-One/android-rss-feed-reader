package com.example.project.dummy;

import com.example.project.logics.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Article> ITEMS = new ArrayList<Article>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Article item) {
        ITEMS.add(item);
    }

    private static Article createDummyItem(int position) {
        Article a = new Article();
        a.setTitle("title");
        a.setGuid(position + "");

        return a;
    }
}
