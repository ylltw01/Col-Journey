package com.sl.coljourney.datastructure.tree;

import java.util.List;

public class Nodes {
    public int val;
    public List<Nodes> children;

    public Nodes() {}

    public Nodes(int _val,List<Nodes> _children) {
        val = _val;
        children = _children;
    }
}
