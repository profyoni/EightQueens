package edu.touro.cs;

// TODO : Replace this file with your code

import javax.swing.*;

class BinaryTree
{
    Node root;
}

class Node
{
    Node leftChild, rightChild;
    Node parent;
}

public class Temp {
    public int dummy(){
        return 1;
    }

    Tuple<StringBuffer, JButton> foo(){
        Tuple<StringBuffer, JButton> t = new Tuple<>();
        t.first = new StringBuffer();
        t.second = new JButton("Boim");
        return t;
    }
}

class Tuple<T1, T2>
{
    T1 first;
    T2 second;
}