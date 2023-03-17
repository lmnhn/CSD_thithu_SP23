/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xForest, int xRate, int xSound) {
        //You should insert here statements to complete this function
        Boo x = new Boo(xForest, xRate, xSound);
        if (x.forest.charAt(0) == 'A') {
        } else {
            if (isEmpty()) {
                root = new Node(x);
                return;
            }
            Node f, p;
            p = root;
            f = null;
            while (p != null) {
                if (p.info == x) {
                    System.out.println(" The key " + x + " already exists, no insertion");
                    return;
                }
                f = p;
                if (x.sound < p.info.sound) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
            if (x.sound < f.info.sound) {
                f.left = new Node(x);
            } else {
                f.right = new Node(x);
            }
        }
    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void preOrder2(Node p, int x, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        } else if (p.info.rate >= 6) {
            preOrder2(p.left, 6, f);
            preOrder2(p.right, 6, f);
        } else {
            fvisit(p, f);
            preOrder2(p.left, 6, f);
            preOrder2(p.right, 6, f);
        }
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preOrder2(root, 6, f);

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void deleteByCopying(Node x) {
        root = deleteByCopyingRec(root, x, 0);
    }

    Node deleteByCopyingRec(Node root, Node x, int c) {
        if (c == 4) {
            Node temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            root.info = temp.info;
            root.right = deleteByCopyingRec(root.right, temp, c++);
            return root;
        } else {
            if (root == null) {
                return null;
            }
            if (x.info.sound < root.info.sound) {
                root.left = deleteByCopyingRec(root.left, x, c++);
            } else if (x.info.sound > root.info.sound) {
                root.right = deleteByCopyingRec(root.right, x, c++);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                Node temp = root.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
//            root.info = temp.info;
                root.right = deleteByCopyingRec(root.right, temp, c++);
            }

        }
        return root;
    }

//=============================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        deleteByCopying(root);

        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
//      Node p = root;
//      int count = 0;
//      while (p.left != null && count != 2) {
//          p = p.left;
//          count++;
//      }
//      if (count < 2) {
//          p = root.right;
//          System.out.println(p.info);
//      }
//      Boo tmp1 = p.left.right.info; // save the RightChild's data
//      Boo tmp2 = p.left.info; //save the 4th node's data
//      p.left.right.left = p.left; // create the left node for the RightChild
//      p.left = null;
//      //create new node
//      p.left = new Node(tmp1);
//      p.left.right = new Node(tmp2);
//      

        //search for 4th node
        int count1 = 0;
        Node q = root; //node 4th tinh root roi nen count = 3
        while (count1 != 3) {
            if (q.left != null) {
                q = q.left;
            } else if (q.right != null) {
                q = q.right;
            } else {
                q = root.right;
            }

            count1++;
        } //after this q = 4th node
        
        //search for node has .left/right.info = q.info or search for node 3th
        int countCase = 0; // 1: tmp.left = q.info
                           // 2: tmp.right = q.info
        Node tmp = root;
        while (true) {
            //check .left.info = q.info
            if (tmp.left != null && tmp.left.info == q.info) {
                countCase = 1;
                break;
               //check .right.info = q.info
            } else if (tmp.right != null && tmp.right.info == q.info) {
                countCase = 2;
                break;
            } else {
                if (tmp.left != null) {
                    tmp = tmp.left;
                } else if (tmp.right != null) {
                    tmp = tmp.right;
                } else {
                    tmp = root.right;
                }
            }
        }
        
        if (countCase == 1) {
            Boo tmp1 = tmp.left.right.info; // save the RightChild's data
            Boo tmp2 = tmp.left.info;       //save the 4th node's data
            tmp.left = null;                // delete the pointer from 3th to 4th node
            // create new node 
            tmp.left = new Node(tmp1);
            tmp.left.left = new Node(tmp2);
        }
        if (countCase == 2) {
            Boo tmp1 = tmp.right.right.info; // save the RightChild's data
            Boo tmp2 = tmp.right.info;       //save the 4th node's data
            tmp.right = null;                // delete the pointer from 3th to 4th node
            //create new node
            tmp.right = new Node(tmp1);
            tmp.right.left = new Node(tmp2);
        }
        //  (C,5,2) (D,2,1) (E,6,5) (F,1,3) (H,3,4) (G,4,6) 
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

}
