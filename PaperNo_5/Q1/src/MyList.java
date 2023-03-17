/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {
  Node head,tail;
  MyList() {head=tail=null;}
  boolean isEmpty() {
    return(head==null);
   }
  void clear() {head=tail=null;}

  void fvisit(Node p, RandomAccessFile f) throws Exception {
    if(p != null) f.writeBytes(p.info + " ");
   }

  void ftraverse(RandomAccessFile f) throws Exception {
    Node p = head;
    while(p!=null) {
       fvisit(p,f); // You will use this statement to write information of the node p to the file
       p=p.next;
      }
    f.writeBytes("\r\n");
   }

  void loadData(int k) { //do not edit this function
    String [] a = Lib.readLineToStrArray("data.txt", k);
    int [] b = Lib.readLineToIntArray("data.txt", k+1);
    int [] c = Lib.readLineToIntArray("data.txt", k+2);
    int n = a.length;
    for(int i=0;i<n;i++) addLast(a[i],b[i],c[i]);
   }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
*/
  void addLast(String xForest, int xRate, int xSound) {
    //You should write here appropriate statements to complete this function.
    if (xForest.charAt(0) == 'B') {
    } else {
        Boo x = new Boo(xForest, xRate, xSound);
         if (isEmpty()) {
           head = tail = new Node(x,null);
       } else {
           Node q = new Node(x,null);
           tail.next = q;
           tail=q;
       }
    }

    }

    void addLast(Boo x) {

        if (isEmpty()) {
            head = tail = new Node(x, null);
        } else {
            Node q = new Node(x, null);
            tail.next = q;
            tail = q;
        }

    }

  //You do not need to edit this function. Your task is to complete the addLast function above only.
  void f1() throws Exception {
     clear();
     loadData(1);
     String fname = "f1.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     ftraverse(f);
     f.close();
    }  
    public void add_Index(Boo x, int index) {
        if (isEmpty()) {
            head = tail = new Node(x, null);
        }
        if (index == 1) {
            if (isEmpty()) {
                head = tail = new Node(x, null);
            } else {
                Node q = new Node(x, head);
                head = q;
            }
        }
        Node q = new Node(x, null);
        Node p = head;
        int i = 1;
        for (;i != index -1 && p!=null;i++) {
            p = p.next;
        }
        q.next = p.next;
        p.next = q;
        if(index == i) {
            addLast(x);
        }
        
    }
//==================================================================
  void f2() throws Exception {
     clear();
     loadData(5);
     String fname = "f2.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     ftraverse(f);
     Boo x, y;
     x = new Boo("X",1,2);
     y = new Boo("Y",3,4);
     //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      add_Index(x, 2);
      add_Index(y, 3);


    //------------------------------------------------------------------------------------
     ftraverse(f);
     f.close();
    }  

     public void delete_index(int index) {
         if (isEmpty()) {
             System.out.println("List is empty!");
             return;
         }
         Node temp = head;

         //delete first
         if (index == 1) {
             if (isEmpty()) {
                 System.out.println("List is empty");
                 return;
             }
             if (head == tail) {
                 clear();
             } else {
                 head = head.next;
             }
        }
        //duyet tim the beforeNode the node thats need to be deleted
        for (int i = 1; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }
        // temp =tail hoac temp = tail.next
        if (temp == null || temp.next == null) {
            return;
        }
        //lien ket new node voi deleteNode.next
        Node next = temp.next.next;
        //lien ket beforeNode vs new node
        temp.next = next;

    }
//==================================================================
  void f3() throws Exception {
    clear();
    loadData(9);
    String fname = "f3.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      delete_index(1);
      delete_index(2);


    //------------------------------------------------------------------------------------
    ftraverse(f);
    f.close();
   }

   public void sort() {
        Node q = new Node();
        boolean swapped =true;
        do {
            swapped = false;
            q = head;
            while (q.next != null) {
                if (q.info.rate > q.next.info.rate) {
                    //swapping
                    Boo temp = q.info;
                    q.info = q.next.info;
                    q.next.info = temp;
                    swapped = true;
                }
                q = q.next;
            }
        } while (swapped);
    }
//==================================================================
  void f4() throws Exception {
    clear();
    loadData(13);
    String fname = "f4.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     
     //sort from 1th to 4th
      Node q =head;
      int count= 0 ,incount = 0; 
      // 11, 7, 5, 6
      while(q.next != null ) {
          if (q.info.rate > q.next.info.rate) {
                    //swapping
                    Boo temp = q.info;
                    q.info = q.next.info;
                    q.next.info = temp;
                }
                q = q.next;
                count ++;
                //7, 5, 6, 11
          if (count == 3) { // bring the largest to 4th then
              q= head;
          }
           if (count ==5){ //point to the 1th and sort
              q= head;
          } 
           if (count >5) {
               break;
           }
      }

    //------------------------------------------------------------------------------------
    ftraverse(f);
    f.close();
   }
   
 }

