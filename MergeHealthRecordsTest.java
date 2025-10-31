
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MergeHealthRecordsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MergeHealthRecordsTest
{

    // Helper method to create a linked list of one record
    private MergeHealthRecords.LinkedList createList(String[][] data) {
        MergeHealthRecords.LinkedList list = new MergeHealthRecords.LinkedList();
        for (String[] d : data) {
            list.insert(new MergeHealthRecords.PatientRecord(d[0], Integer.parseInt(d[1]), d[2]));
        }
        return list;
    }

    // Helper method to convert list to array of SSNs for comparison
    private String[] toArray(MergeHealthRecords.Node head) {
        java.util.ArrayList<String> result = new java.util.ArrayList<>();
        MergeHealthRecords.Node temp = head;
        while (temp != null) {
            result.add(temp.data.ssn);
            temp = temp.next;
        }
        return result.toArray(new String[0]);
    }

    // 3 normal test cases
    @Test
    public void testMergeNormalNoDuplicates(){
        MergeHealthRecords.LinkedList list1 =  createList(new String[][]{
                    {"111-11-1111", "30", "Alice"},
                    {"222-22-2222", "40", "Bob"}
                });
        MergeHealthRecords.LinkedList list2 =  createList(new String[][]{
                    {"333-33-3333", "30", "Alice"},
                    {"444-44-4444", "40", "Bob"}
                });

        MergeHealthRecords.Node result = MergeHealthRecords.mergeLists(list1.head, list2.head);
        assertArrayEquals(new String[]{"111-11-1111", "222-22-2222", "333-33-3333", "444-44-4444"},
            toArray(result));
    }

    @Test
    public void testMergeNormalNoDuplicatesDifferentAmounts(){
        MergeHealthRecords.LinkedList list1 =  createList(new String[][]{
                    {"111-11-1111", "30", "Alice"},
                    {"222-22-2222", "40", "Bob"},
                    {"888-88-8888", "55", "Robert"}

                });
        MergeHealthRecords.LinkedList list2 =  createList(new String[][]{
                    {"333-33-3333", "30", "Alice"},
                    {"444-44-4444", "40", "Bob"}
                });

        MergeHealthRecords.Node result = MergeHealthRecords.mergeLists(list1.head, list2.head);
        assertArrayEquals(new String[]{"111-11-1111", "222-22-2222", "333-33-3333", "444-44-4444", "888-88-8888"},
            toArray(result));
    }

    @Test
    public void testMergeNormalWithDuplicate(){
        MergeHealthRecords.LinkedList list1 =  createList(new String[][]{
                    {"111-11-1111", "30", "Alice"},
                    {"222-22-2222", "40", "Bob"},
                    {"888-88-8888", "55", "Robert"}

                });
        MergeHealthRecords.LinkedList list2 =  createList(new String[][]{
                    {"333-33-3333", "30", "Alice"},
                    {"444-44-4444", "40", "Bob"},
                    {"888-88-8888", "55", "Robert"}

                });

        MergeHealthRecords.Node result = MergeHealthRecords.mergeLists(list1.head, list2.head);
        assertArrayEquals(new String[]{"111-11-1111", "222-22-2222", "333-33-3333", "444-44-4444", "888-88-8888",
                "888-88-8888"},
            toArray(result));
    }

    // 3 Edge Test Cases
    @Test
    public void testMergeEmptyList(){
        MergeHealthRecords.LinkedList list1 =  createList(new String[][]{
                    });
        
        MergeHealthRecords.LinkedList list2 =  createList(new String[][]{
                {"111-11-1111", "30", "Alice"}});
        
        MergeHealthRecords.Node result = MergeHealthRecords.mergeLists(list1.head, list2.head);
        
        assertArrayEquals(new String[] {"111-11-1111"}, toArray(result));
    }
    @Test
    public void testMergeSecondEmptyList(){
        MergeHealthRecords.LinkedList list1 =  createList(new String[][]{
                   {"111-11-1111", "30", "Alice"} });
        
        MergeHealthRecords.LinkedList list2 =  createList(new String[][]{
                });
        
        MergeHealthRecords.Node result = MergeHealthRecords.mergeLists(list1.head, list2.head);
        
        assertArrayEquals(new String[] {"111-11-1111"}, toArray(result));
    }    
    @Test
    public void testMergeBothEmptyList(){
        MergeHealthRecords.LinkedList list1 =  createList(new String[][]{
                   });
        
        MergeHealthRecords.LinkedList list2 =  createList(new String[][]{
                });
        
        MergeHealthRecords.Node result = MergeHealthRecords.mergeLists(list1.head, list2.head);
        
        assertArrayEquals(new String[] {}, toArray(result));
    }      
    }

