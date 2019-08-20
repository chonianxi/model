package com.model.tree.preordering_tree;

import java.util.LinkedList;

public class PreorderingTree {
    private int id;
    private int lft;
    private int rgt;
    private LinkedList<PreorderingTree> childNode;
    //上级
    private int superior;
    private Object value;

    public PreorderingTree(){

    }

    public PreorderingTree(int id,int lft,int rgt,int superior){
        this.id = id;
        this.lft = lft;
        this.rgt = rgt;
        this.superior = superior;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    public LinkedList<PreorderingTree> getChildNode() {
        return childNode;
    }

    public void setChildNode(LinkedList<PreorderingTree> childNode) {
        this.childNode = childNode;
    }

    public int getSuperior() {
        return superior;
    }

    public void setSuperior(int superior) {
        this.superior = superior;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
