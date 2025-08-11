package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                break;
            }
            item.sellIn = item.sellIn - 1;

            if (itemAgesGracefully(item)) {
                ageGracefully(item);
            } else {
                boolean agedOut = item.sellIn < 0;
                changeQualityOf(item, -1 - (agedOut ? 1 : 0));
            }
        }
    }

    private static boolean itemAgesGracefully(Item item) {
        return item.name.equals("Aged Brie")
            || item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void ageGracefully(Item item) {
        int amountToAgeGracefully = 1;

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            amountToAgeGracefully = getBackstagePassGracefulIncrease(item);
        }
        if (item.name.equals("Aged Brie")) {
            amountToAgeGracefully = 1 + (item.sellIn < 0 ? 1 : 0);
        }
        changeQualityOf(item, amountToAgeGracefully);
    }

    private static int getBackstagePassGracefulIncrease(Item item) {
        if (item.sellIn < 0) {
            return item.quality * -1;
        }
        if (item.sellIn < 6) {
            return 3;
        }
        if (item.sellIn < 11) {
            return 2;
        }
        return 1;
    }

    private void changeQualityOf(Item item, int by) {
        if (by > 0 && item.quality + by > 50) {
            item.quality = 50;
        } else if (by < 0 && item.quality + by < 0) {
            item.quality = 0;
        } else {
            item.quality = item.quality + by;
        }
    }

    private void decrementQualityOf(Item item) {
        changeQualityOf(item, -1);
    }

    private void incrementQualityOf(Item item) {
        changeQualityOf(item, 1);
    }
}
