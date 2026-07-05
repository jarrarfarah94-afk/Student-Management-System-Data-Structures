
package com.mycompany.dsproject;

import java.util.Scanner;


public class StudentManagementSystem {
    
    static class Student {   //constructors
        int rollNo;
        String name;
        double marks;

        Student(int r, String n, double m) {
            rollNo = r;
            name = n;
            marks = m;
        }

        void display() {  // to print students data
            System.out.println(rollNo + " | " + name + " | " + marks);
        }
    }
    
        //  Unsorted List 
    static Student[] students = new Student[50];
    static int count = 0;  

    static void addStudentArray(Student s) { //adding a student to the array
        if (count < students.length) {  // being sure it is 50 or less 
            students[count] = s;
            count++;
            System.out.println("Student added to array.");
        } else {
            System.out.println("Array is full!");
        }
    }

    static void displayArray() {
        if (count == 0) {  // make sure the array is not empty 
            System.out.println("No students found.");
            return; 
        }
        for (int i = 0; i < count; i++) {
            students[i].display(); //print data that it is index i 
        }
    }

    static void searchArray(int roll) {
        for (int i = 0; i < count; i++) {
            if (students[i].rollNo == roll) {
                System.out.println("Student found:");
                students[i].display();
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
   static void deleteStudentArray(int roll)
   { 
    for (int i = 0; i < count; i++) {
           if(students[i].rollNo==roll){
          for (int j = i; j < count-1; j++) 
              
               students[j]=students[j+1];  // shift left
                       students[count -1] = null;
                       count--;  // -1 from the array
                System.out.println("Student deleted from array.");
                return;
            }
        }
        System.out.println("Student not found.");
    }


   // Sorted List 
  static class Node{ // constructors
      Student data;
      Node next;
      
      Node (Student s){
          data=s;
          next=null;
      }
  }
  
  static Node head=null; 
  
  static void addStudentList(Student s){
      Node newNode =new Node(s); //new object 
      
       if (head == null || s.rollNo < head.data.rollNo) {
                newNode.next = head;
                head = newNode;
            } else {
                Node temp = head;// first element 
                while (temp.next != null && temp.next.data.rollNo < s.rollNo) {
                    temp = temp.next; //next student 
                }
                newNode.next = temp.next;
                temp.next = newNode;
            }
            System.out.println("Student added to sorted linked list.");
        }
     
  static void displayList(){
      if (head==null){
          System.out.println("List is empty.");
      return;
            }
   Node temp =head ;   // temp is a tempriory variable 
   while (temp != null){     
   temp.data.display();
   temp= temp.next;
  }}

  
  static void searchList(int roll){
      Node temp= head;
      while (temp!=null){
          if (temp.data.rollNo == roll){
              System.out.println("Student found in linked list");
              temp.data.display();
              return;
          }
      temp = temp.next; // from the node to the next node 
      }
  
      System.out.println("Student not found in linked list :");
      
  }
  
  static void deleteStudentList(int roll){
      if (head==null){
          System.out.println("List is empty");
          return;
      }
      if (head.data.rollNo == roll){ // if the head is the one we want to delete 
          head = head.next;  // the new head will be the next one beside the head 
          System.out.println("Studnet deleted from Linked List");
          return;
      }
      
      Node prev = head; // prevouis element before the element we want to delete 
      Node curr = head.next; //element we want to delete 
      while(curr != null){
          if(curr.data.rollNo==roll){
              prev.next=curr.next;
              System.out.println("Studnet deleted from Linked List");
              return;
          }
          prev=curr;
          curr= curr.next;
      }
      System.out.println("Student not found in linked list.");
          
  }
  //Stack (Undo-Redo)
  static int[] undoStack = new int[10];
  static int undoTop = -1;
  
  static int[] redoStack = new int[10];
  static int redoTop = -1;
  
  //push to undo stack 
  
  static void pushUndo(int roll){
      if(undoTop < undoStack.length-1){ // making sure the stack is not full
          undoTop++; 
          undoStack[undoTop] = roll; //storing the student 
          redoTop = -1;
      }
  }
  //Undo 
  static void undo(){
      if(undoTop == -1){
          System.out.println("Nothing to undo");
          return;
      }
      
          int roll = undoStack[undoTop]; // last student 
          undoTop--; // remove him from the stack 
          if (redoTop < redoStack.length -1){
           redoTop++;
           redoStack[redoTop] = roll;
      }
      System.out.println("Undo last action for student roll no:"+roll);

  }
  //redo 
  static void redo(){
      if (redoTop == -1){
          System.out.println("Nothing to redo");
          return;
      }
     
          int roll = redoStack[redoTop];
          redoTop--;
          if (undoTop < undoStack.length -1){
                   undoTop++;
              undoStack[undoTop] = roll;
      }
                    System.out.println("Redo action for student roll no: "+ roll);

  }
  // Queue (wating students)
  static Student[] queue= new Student[10];
  static int front = 0;
  static int rear =-1;
  
  static void addToQueue(Student s){
      if (rear < queue.length-1){
         rear++;
          queue[rear] = s;
          
          System.out.println("Student added to waiting queue:"+ s.rollNo + " | "+ s.name);
      }
      else 
      {
          System.out.println("Queue is full.");
      }
      
  }
  static void removeFromQueue(){
      if(front>rear){
          System.out.println("Queue is empty.");
          return;
      }
       
          System.out.println("Removed from queue: ");
          queue[front].display();
          front++;
      
              
  }
  
  static void displayQueue(){
      if(front > rear ){
          System.out.println("Queue is empty.");
          return;
      }
     
     System.out.println("Removed from queue: ");
          for (int i = front; i <= rear; i++) {
              queue[i].display();
          
      }
  }
  
  //Main

public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Add Student");
        System.out.println("2. Display Array (Unsorted)");
        System.out.println("3. Display Linked List");
        System.out.println("4. Search in Array");
        System.out.println("5. Delete from Array");
        System.out.println("6. Undo");
        System.out.println("7. Redo");
        System.out.println("8. Add to Queue");
        System.out.println("9. Remove from Queue");
        System.out.println("10. Display Queue");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
        
        choice =sc.nextInt();
        
        switch (choice){
            case 1 :
                System.out.println("Roll No: ");
                int r = sc.nextInt();
                sc.nextLine();
                System.out.println("Name: ");
                String n = sc.nextLine();
                System.out.println("Marks: ");
                double m = sc.nextDouble();
                
                StudentManagementSystem.Student s = new StudentManagementSystem.Student(r, n, m);
                StudentManagementSystem.addStudentArray(s);
                StudentManagementSystem.addStudentList(s);
                StudentManagementSystem.pushUndo(r);
                break;
                
            case 2:
            StudentManagementSystem.displayArray();
            break;
            
            case 3:
                StudentManagementSystem.displayList();
                break;
                
            case 4:
                System.out.println("Enter roll number: ");
                StudentManagementSystem.searchArray(sc.nextInt());
                break;
                
            case 5:
                System.out.println("Enter roll number to delete: ");
                StudentManagementSystem.deleteStudentArray(sc.nextInt());
                break;
                
            case 6:
                StudentManagementSystem.undo();
                break;
                
            case 7 :
                StudentManagementSystem.redo();
                break;
                
            case 8:
                StudentManagementSystem.addToQueue(new StudentManagementSystem.Student(999, "WaitingStudent",0));
            break;
            
               case 9:
                StudentManagementSystem.removeFromQueue();
                break;

            case 10:
                StudentManagementSystem.displayQueue();
                break;
        }
        }
        while(choice != 0);

}}



