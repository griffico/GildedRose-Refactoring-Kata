package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void itemsHaveSellInAndQuality() {
        int sellIn = 0;
        int quality = 0;
        Item[] items = new Item[]{new Item("foo", sellIn, quality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(quality - 1, app.items[0].sellIn);
    }

    @Test
    void qualityDegrades() {
        int sellIn = 100;
        int quality = 100;
        Item[] items = new Item[]{new Item("foo", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(quality - 1, app.items[0].quality);
    }

    @Test
    void qualityDegradesTwiceAsFastAfterExpiration() {
        int sellIn = 0;
        int quality = 100;
        Item[] items = new Item[]{new Item("foo", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(quality - 2, app.items[0].quality);
    }

    @Test
    void qualityDegradesTwiceAsFastForConjured() {
        int sellIn = 50;
        int quality = 75;
        Item[] items = new Item[]{new Item("Conjured", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(quality - 2, app.items[0].quality);
    }

    @Test
    void qualityWontGoBelowNegative() {
        int sellIn = 5;
        int quality = 0;
        Item[] items = new Item[]{new Item("foo", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityIncreasesForBrie() {
        int sellIn = 5;
        int quality = 0;
        Item[] items = new Item[]{new Item("Aged Brie", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].quality);
    }

    @Test
    void qualityIncreasesForBriePastSellIn() {
        int sellIn = 0;
        int quality = 0;
        Item[] items = new Item[]{new Item("Aged Brie", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(2, app.items[0].quality);
    }

    @Test
    void qualityIsNeverMoreThan50() {
        int sellIn = 0;
        int quality = 50;
        Item[] items = new Item[]{new Item("Aged Brie", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sulfurasNeverChangesQuality() {
        int expectedQuality = 3;
        int sellIn = 2;

        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", sellIn, expectedQuality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void backstagePassesIncreaseInvalue() {
        int quality = 3;
        int sellIn = 15;

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].quality);
    }

    @Test
    void backstagePassesIncreaseInValueDoubleAfter10Days() {
        int quality = 3;
        int sellIn = 10;

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].quality);
    }

    @Test
    void backstagePassesIncreaseInValueTripleAfter5Days() {
        int quality = 3;
        int sellIn = 5;

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }

    @Test
    void backstagePassesZeroAfterConcert() {
        int quality = 3;
        int sellIn = 0;

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)};

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

}
