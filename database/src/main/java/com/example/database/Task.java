@Entity
public class Task{
    @PrimaryKey
    public int taskId;
    public String taskContent;
    public int taskPriority;
    public long taskCreationDate;
    public long taskDeadLine;
}
