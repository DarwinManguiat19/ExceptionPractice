package service;
 
import exception.DuplicateRecordException;
import java.util.ArrayList;
import model.ItemRecord;
 
public class RecordManager {
 
    private final ArrayList<ItemRecord> records = new ArrayList<ItemRecord>();
 
    // Rule: IDs are compared ignoring case (R001 and r001 are the same).
    public ItemRecord findById(String recordId) {
        if (recordId == null) {
            return null;
        }
        String wanted = recordId.trim();
 
        for (ItemRecord record : records) {
            if (record.getRecordId().equalsIgnoreCase(wanted)) {
                return record;
            }
        }
        return null;
    }
 
    public void addRecord(ItemRecord newRecord)
            throws DuplicateRecordException {
        if (findById(newRecord.getRecordId()) != null) {
            throw new DuplicateRecordException("identifier "
                    + newRecord.getRecordId() + " already exists.");
        }
        records.add(newRecord);
    }
 
    public void displayRecords() {
        if (records.isEmpty()) {
            System.out.println("No records have been added yet.");
            return;
        }
 
        System.out.println();
        System.out.println("Accepted records:");
        System.out.printf("%-10s %-20s %8s%n", "ID", "Name", "Quantity");
        System.out.println("----------------------------------------");
        for (ItemRecord record : records) {
            System.out.printf("%-10s %-20s %8d%n", record.getRecordId(),
                    record.getName(), record.getQuantity());
        }
        System.out.println("Total records: " + records.size());
    }
 
    public int getRecordCount() {
        return records.size();
    }
}
