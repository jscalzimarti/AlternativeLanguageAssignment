import java.util.*;

public class DataManager {
    private HashMap<Integer, Cell> cellMap;
    private int idCounter;

    public DataManager() {
        this.cellMap = new HashMap<>();
        this.idCounter = 0;
    }

    public void addCell(Cell cell) {
        cellMap.put(idCounter++, cell);
    }

    public void removeCell(int id) {
        if (cellMap.containsKey(id)) {
            cellMap.remove(id);
        } else {
            System.out.println("Cell with ID " + id + " does not exist.");
        }
    }
    
    public Set<String> getUniqueOEMs() {
        Set<String> uniqueOEMs = new HashSet<>();
        for (Cell cell : cellMap.values()) {
            uniqueOEMs.add(cell.getOem());
        }
        return uniqueOEMs;
    }
    
    public boolean cellExists(int id) {
        return cellMap.containsKey(id);
    }
    
    public List<Cell> getCells() {
        return new ArrayList<>(cellMap.values());
    }
    
    public Cell getCellById(int id) {
        if (cellExists(id)) {
            return cellMap.get(id);
        } else {
            return null;
        }
    }
}