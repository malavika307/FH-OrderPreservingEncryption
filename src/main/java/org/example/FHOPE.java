package org.example;

import java.util.*;

public class FHOPE {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer plain;  // bitstring<k>
        Integer cipher; // bitstring<l>

        TreeNode(Integer plain, Integer cipher) {
            this.plain = plain;
            this.cipher = cipher;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;
    private Random random;
    private final int k;
    private final double l;
    private final int globalMax;
    private int encr;

    public FHOPE(int numberOfPlaintexts, double securityParameter) {
        this.root = null;
        this.k = numberOfPlaintexts;
        this.l = securityParameter;
        globalMax = (int) Math.pow(k, l);
        this.random = new Random();
    }

    // Random Coin function
    private int randomCoin() {
        return random.nextInt(2); // returns 0 or 1
    }

    // Encrypt function
    public int encrypt(int x) {
        int min = 0;
        int max = globalMax;
        if (root == null) {
            root = new TreeNode(x, max / 2);
            return root.cipher;
        }
        return encryptRecursive(x, root, min, max);
    }

    private int encryptRecursive(int x, TreeNode t, int min, int max) {
        if (x == t.plain) {
            int coin = randomCoin();
            if (coin == 1) {
                if (t.right != null) {
                    return encryptRecursive(x, t.right, t.cipher, max);
                } else {
                    if (max - t.cipher < 2) {
//                        System.out.println("Rebalance 1---");
                        return rebalance(x, 0, globalMax);
                    }
                    t.right = new TreeNode(x, t.cipher + (max - t.cipher) / 2);
                    return t.right.cipher;
                }
            } else {
                if (t.left != null) {
                    return encryptRecursive(x, t.left, min, t.cipher);
                } else {
                    if (t.cipher - min < 2) {
//                        System.out.println("Rebalance 2---");
                        return rebalance(x, 0, globalMax);
                    }
                    t.left = new TreeNode(x, (min + ((t.cipher) - min) / 2));
                    return t.left.cipher;
                }
            }
        } else if (x > t.plain) {
            if (t.right != null) {
                return encryptRecursive(x, t.right, (t.cipher), max);
            } else {
                if (max - (t.cipher) < 2) {
//                    System.out.println("Rebalance 3---");
                    return rebalance(x, 0, globalMax);
                }
                t.right = new TreeNode(x, ((t.cipher) + (max - (t.cipher)) / 2));
                return t.right.cipher;
            }
        } else {
            if (t.left != null) {
                return encryptRecursive(x, t.left, min, (t.cipher));
            } else {
                if ((t.cipher) - min < 2) {
//                    System.out.println("Rebalance 4---");
                    return rebalance(x, 0, globalMax);
                }
                t.left = new TreeNode(x, (min + ((t.cipher) - min) / 2));
                return t.left.cipher;
            }
        }
    }

    // Decrypt function
    public int decrypt(int y) {
        return decryptRecursive(y, root);
    }

    private int decryptRecursive(int y, TreeNode t) {
        if (t != null) {
            if (y == t.cipher) {
                return t.plain;
            } else if ((y) > (t.cipher)) {
                return decryptRecursive(y, t.right);
            } else {
                return decryptRecursive(y, t.left);
            }
        }
        return -1;
    }

    // Rebalance function
    private int rebalance(int x, int min, int max) {
        List<TreeNode> nodes = new ArrayList<>();
        collectNodes(root, nodes);
        nodes.add(new TreeNode(x, null));
        nodes.sort(Comparator.comparing(a -> a.plain));
        this.root = buildBalancedTree(nodes, min, max);
        return encr;
    }

    // Collect all nodes in the tree
    private void collectNodes(TreeNode node, List<TreeNode> nodes) {
        if (node != null) {
            collectNodes(node.left, nodes);
            nodes.add(node);
            collectNodes(node.right, nodes);
        }
    }

    // Build a balanced tree from the sorted list of nodes
    private TreeNode buildBalancedTree(List<TreeNode> nodes, int min, int max) {
        if (nodes.isEmpty()) {
            return null;
        }
        int mid = (nodes.size() - 1)/ 2;

        TreeNode node = nodes.get(mid);

        int tmpCipher = (min + max) / 2;
        if(node.cipher==null) encr = tmpCipher;
        node.cipher = tmpCipher;

        node.left = buildBalancedTree(nodes.subList(0, mid), min , (node.cipher) - 1);
        node.right = buildBalancedTree(nodes.subList(mid + 1, nodes.size()), (node.cipher) + 1, max);
        return node;
    }

    private void inOrderTraversalAndDecrypt(TreeNode node) {
        if (node != null) {
            inOrderTraversalAndDecrypt(node.left);
            System.out.println("Ciphertext: " + node.cipher + ", Plaintext: " + decrypt(node.cipher));
            inOrderTraversalAndDecrypt(node.right);
        }
    }


    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: FHOPE <numberOfPlaintexts> <securityParameter> <plaintext1> <plaintext2> ...");
            System.exit(1);
        }

        int numberOfPlaintexts = Integer.parseInt(args[0]);
        int securityParameter = Integer.parseInt(args[1]);
        int[] plaintexts = new int[args.length - 2];
        for (int i = 0; i < plaintexts.length; i++) {
            plaintexts[i] = Integer.parseInt(args[i + 2]);
        }

        FHOPE fhope = new FHOPE(numberOfPlaintexts, securityParameter);
        for (int plaintext : plaintexts) {
            fhope.encrypt((plaintext));
        }

        //In-order traversal to print the tree's ciphertext values and decrypt them
        System.out.println("Post encryption:");
        fhope.printTree();

        System.out.println("Tree nodes in-order with decryption:");
        fhope.inOrderTraversalAndDecrypt(fhope.root);


    }
    // Method to print tree values
    public void printTree() {
        printTreeRecursive(root);
    }

    private void printTreeRecursive(TreeNode node) {
        if (node != null) {
            System.out.println("Plain: " + node.plain + ", Cipher: " + node.cipher);
            printTreeRecursive(node.left);
            printTreeRecursive(node.right);
        }
    }
}
