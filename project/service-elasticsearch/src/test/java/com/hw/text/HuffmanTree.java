package com.hw.text;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/1/19
 * @author WX964987
 */
public class HuffmanTree {

    public static class Node<E> {
        E e;
        int weight;
        Node<E> leftChild;// 左节点
        Node<E> rightChild;// 右节点
        public Node(E e, int weight) {
            this.e = e;
            this.weight = weight;
        }
        public Node(E e, int weight, Node<E> leftChild, Node<E> rightChild) {
            this.e = e;
            this.weight = weight;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    /**
     * 创建哈夫曼树－－并返回根节点
     *
     * @param nodeList
     * @return 先对所有的节点进行排序，-->根据节点的数据大小从大到小排序-->每次取最小的两个元素，组合一个新的元素
     *         －－》将新元素放到集合中，最合的集合只剩一个元素，就是根元素
     */
    private Node<String> createHuffmanTree(List<Node<String>> nodeList) {
        while (nodeList.size() != 1) {// 最后剩一个元素时退出循环
            // 根据元素的权重（weight）进行从大到小排序
            nodeListSort(nodeList);
            // 取最小的两个元素－－》组成一个新的元素
            int size = nodeList.size();
            Node<String> left = nodeList.get(size - 1);
            Node<String> right = nodeList.get(size - 2);
            Node<String> newNode = new Node<String>("new", left.weight + right.weight, left, right);
            // 将最小的两个元素删除，并添加新生成的节点元素
            nodeList.remove(left);
            nodeList.remove(right);
            nodeList.add(newNode);
        }
        return nodeList.get(0);
    }
    /**
     * 使用插入排序按照元素的weight进行从大到小排序
     *
     * @param nodeList
     */
    private void nodeListSort(List<Node<String>> nodeList) {
        int size = nodeList.size();
        for (int i = 1; i < size; i++) {
            Node<String> node = nodeList.get(i);
            int j = i - 1;
            // 第0个比第1个小
            for (; j >= 0; j--) {
                if (nodeList.get(j).weight < node.weight) {
                    swap(nodeList, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }
    /**
     * List中的位置i和位置j的元素进行交换
     *
     * @param nodeList
     * @param j
     * @param i
     */
    private void swap(List<Node<String>> nodeList, int j, int i) {
        Node<String> temp = nodeList.get(j);
        nodeList.set(j, nodeList.get(i));
        nodeList.set(i, temp);
    }
    public static void main(String[] args) {
        HuffmanTree hTree = new HuffmanTree();
        List<Node<String>> nodeList = new ArrayList<Node<String>>();
        nodeList.add(new Node<String>("A", 12));
        nodeList.add(new Node<String>("B", 2));
        nodeList.add(new Node<String>("C", 4));
        nodeList.add(new Node<String>("D", 2));
        nodeList.add(new Node<String>("E", 9));
        nodeList.add(new Node<String>("F", 15));
        Node<String> rootNode = hTree.createHuffmanTree(nodeList);
        // 中序遍历二叉树
        midTraversalTree(rootNode);
    }
    /**
     * 中序遍历二叉树
     *
     * @param node
     */
    private static void midTraversalTree(Node<String> node) {
        if (node != null) {
            midTraversalTree(node.leftChild);
            System.out.println("节点：" + node.e + " 权重：" + node.weight);
            midTraversalTree(node.rightChild);
        }
    }
}
