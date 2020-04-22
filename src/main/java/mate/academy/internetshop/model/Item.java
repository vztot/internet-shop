package mate.academy.internetshop.model;

import java.math.BigDecimal;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class Item {
    private Long itemId;
    private String name;
    private BigDecimal price;

    public Item(String name, BigDecimal price) {
        this.itemId = generateId();
        this.name = name;
        this.price = price;
    }

    public static long generateId() {
        long min = 1000000000000000000L;
        long max = Long.MAX_VALUE;
        long delta = (long) (random() * (abs(min - max) + 1));
        return min + delta;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
