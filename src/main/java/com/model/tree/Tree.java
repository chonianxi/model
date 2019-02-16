package com.model.tree;

import java.io.Serializable;
import java.util.LinkedList;

public class Tree implements Serializable{

    private static final long serialVersionUID = -6748041784686592411L;

    //树的当前节点ID
    public int nodeId = 1;
    //树的儿子最大的节点ID
    public int sunNodeMaxId;
    //下面节点的集合
    public LinkedList<Tree> sunList;
    //节点类型,0根结点，1树干，2叶子节点
    public int nodeType;

    //批量更新用fork join

    public Tree(int nodeId){
        this.nodeId = nodeId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getSunNodeMaxId() {
        return sunNodeMaxId;
    }

    public void setSunNodeMaxId(int sunNodeMaxId) {
        this.sunNodeMaxId = sunNodeMaxId;
    }

    public LinkedList<Tree> getSunList() {
        return sunList;
    }

    public void setSunList(LinkedList<Tree> sunList) {
        this.sunList = sunList;
    }

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }
}
