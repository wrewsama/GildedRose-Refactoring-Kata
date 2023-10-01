package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Aged Brie
 *  if quality < 50 then incr by 1
 *  if sellin < 1 and qual == 49 then incr by 1
 *  if sellin < 1 and qual < 49 then incr by 2
 *  decr sellin by 1
 * Backstage passes to a TAFKAL80ETC concert
 *   if qual < 50 and sellin > 11 then incr qual by 1
 *   if qual < 49 and 0 < sellin < 6 then incr qual by 2
 *   if qual < 50 and 0 < sellin < 11 then incr qual by 1
 *   decr sellin by 1
 *   if sellin < 1 then set qual = 0
 * Sulfuras, Hand of Ragnaros
 *   decr sellIn
 * Others
 *   if quality > 0 then decrease by 1
 *   if sellin < 0 and qual > 0 then decr by 1
 */
class GildedRoseTest {

    @Test
    void when_agedbrie_has_quality_lt_50_then_incr_by_1() {
        Item i1 = new Item("Aged Brie", 0, 49);
        Item i2 = new Item("Aged Brie", 0, 50);
        Item[] items = new Item[] { i1, i2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }

    @Test
    void when_agedbrie_has_sellin_lt_1_and_quality_lt_49_then_incr_by_2() {
        Item i1 = new Item("Aged Brie", 0, 48);
        Item i2 = new Item("Aged Brie", 0, 50);
        Item[] items = new Item[] { i1, i2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }
    @Test
    void when_agedbrie_has_sellin_lt_1_and_quality_eq_49_then_incr_by_1() {
        Item i = new Item("Aged Brie", 0, 49);
        Item[] items = new Item[] { i };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
    void when_agedbrie_then_decr_sellin_by_1() {
        Item i = new Item("Aged Brie", 69, 420);
        Item[] items = new Item[] { i };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(68, app.items[0].sellIn);
    }

    @Test
    void when_backstagepass_then_decr_sellin_by_1() {
        Item i = new Item("Backstage passes to a TAFKAL80ETC concert", 69, 420);
        Item[] items = new Item[] { i };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(68, app.items[0].sellIn);
    }
    @Test
    void when_backstagepass_has_quality_lt_50_and_sellin_gt_10_then_incr_by_1() {
        Item i1 = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 49);
        Item i2 = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50);
        Item[] items = new Item[] { i1, i2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }
    @Test
    void when_backstagepass_has_sellin_lt_1_then_set_quality_to_0() {
        Item i1 = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 49);
        Item i2 = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 50);
        Item[] items = new Item[] { i1, i2 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    void when_backstagepass_has_sellin_between_0_and_6_and_quality_lt_48_then_incr_quality_by_3() {
        Item i1 = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 47);
        Item i2 = new Item("Backstage passes to a TAFKAL80ETC concert", 2, 46);
        Item i3 = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 46);
        Item[] items = new Item[] { i1, i2, i3 };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(49, app.items[1].quality);
        assertEquals(48, app.items[2].quality);
    }
}
