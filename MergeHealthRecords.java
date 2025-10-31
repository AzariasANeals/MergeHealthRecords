
/**
 * Write a description of class MergeHealthRecords here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MergeHealthRecords
{
    static class PatientRecord{
        String ssn;
        int age;
        String fullName;

        public PatientRecord(String ssn, int age, String fullName){
            this.ssn = ssn;
            this.age = age;
            this.fullName = fullName;
        }

        @Override
        public String toString(){
            return "SSN: " + ssn + ", Age: " + age + ", Name: " + fullName;
        }

    }

    static class Node{
        PatientRecord data;
        Node next;

        public Node(PatientRecord data){
            this.data = data;
            this.next = null;
        }

    }

    static class LinkedList{
        Node head;

        public void insert(PatientRecord record){
            Node newNode = new Node(record);

            if (head == null){
                head = newNode;
                return;
            }

            Node temp = head;
            while( temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }

        public void display(){
            Node temp = head;
            while (temp != null){
                System.out.println(temp.data);
                temp = temp.next;
            }
        }

    }

    public static Node mergeLists(Node head1, Node head2){
        Node dummy = new Node(null);
        Node current = dummy;

        while( head1 != null && head2 != null){
            if(head1.data.ssn.compareTo(head2.data.ssn) <= 0){
                current.next = head1;
                head1 = head1.next;
            }
            else{
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;

        }

        if (head1 != null) current.next = head1;
        if (head2 != null) current.next = head2;

        return dummy.next;
    }

    public static void main(String[] args){
        LinkedList healthCareProvider1 = new LinkedList();
        healthCareProvider1.insert(new PatientRecord("111-22-3333", 25, "Azarias A'Neals"));
        healthCareProvider1.insert(new PatientRecord("122-23-3333", 25, "Azarias A'Neals"));
        healthCareProvider1.insert(new PatientRecord("133-24-3333", 25, "Azarias A'Neals"));

        LinkedList healthCareProvider2 = new LinkedList();
        healthCareProvider2.insert(new PatientRecord("111-20-3333", 25, "Azarias A'Neals"));
        healthCareProvider2.insert(new PatientRecord("122-25-3333", 25, "Azarias A'Neals"));
        healthCareProvider2.insert(new PatientRecord("133-22-3333", 25, "Azarias A'Neals"));

        System.out.println("Health Care Provider 1 Records: ");
        healthCareProvider1.display();
        
        System.out.println("Health Care Provider 2 Records: ");
        healthCareProvider2.display();
        
        System.out.println("");
        
        Node mergedLists = mergeLists(healthCareProvider1.head, healthCareProvider2.head);
        
        System.out.println("\nMerged Patient Records: ");
        Node temp = mergedLists;
        while( temp!= null){
            System.out.println(temp.data);
            temp = temp.next;
        }
        
    }
}