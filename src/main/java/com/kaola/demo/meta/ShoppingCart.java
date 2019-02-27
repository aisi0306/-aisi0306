package com.kaola.demo.meta;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: li ying
 * @Date: 2019/2/10 19:25
 */
@Data
public class ShoppingCart {
    private int id;

    private int userId;

    private int contentId;

    private int num;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart that = (ShoppingCart) o;
        return getUserId() == that.getUserId() &&
                getContentId() == that.getContentId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getContentId());
    }
}
