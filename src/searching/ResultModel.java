package searching;

public class ResultModel {


    private int FoundCount;
    private String Position;
    private long ExecutionTime;

    public ResultModel(int FoundCount) {
        this.FoundCount = FoundCount;
    }

    public ResultModel() {
        // TODO Auto-generated constructor stub
        FoundCount = 0;
        Position = "";
    }

    public int getFoundCount() {

        return FoundCount;
    }

    public void setFoundCount(int FoundCount) {
        this.FoundCount = FoundCount;
    }

    public String getPosition() {

        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public long getExecutionTime() {

        return ExecutionTime;
    }

    public void setExecutionTime(long ExecutionTime) {
        this.ExecutionTime = ExecutionTime;
    }

}
