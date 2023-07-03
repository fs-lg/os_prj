import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Main {
    int r1,r2,r3;
    int typeAl;
    LinkedList<Task> tasks = new LinkedList<Task>();
    LinkedList<Task> ready = new LinkedList<Task>();

    LinkedList<Task> waiting = new LinkedList<Task>();
    int nT;


    public static void addToReady(Task t, LinkedList<Task> rd , int type){
        switch (type){
            case 1 : {ADDfcfs(rd , t);
                    break;
            }
            case 2 : {
                ADDsjf(rd, t);
                break;
            }
            case 3 : {
                ADDfcfs(rd , t);//rrb in adding is like fcfs !
                break;
            }
        }

    }
    public static void ADDsjf(LinkedList<Task> q , Task t){
        int i=0;
        for (; i < q.size(); i++) {
            if(t.getTaskD()<q.get(i).getTaskD())break;
        }
        q.add(i, t);
    }


    public static void ADDfcfs(LinkedList<Task>q , Task t){
        q.addLast(t);
    }
    public static Task getfcfs(LinkedList<Task> q){

        return q.removeFirst();

    }

    public  boolean freeR(Task t , Main m ) {
        if (t.getTaskType() == "X") {
            if(m.r1!=0 && m.r2!=0){
                m.r1--;
                m.r2--;
                return true;
            }
            else return false;

        } else if (t.getTaskType() == "Y") {
            if(m.r3!=0 && m.r2!=0){
                m.r3--;
                m.r2--;
                return true;
            }
            else return false;

        } else if (t.getTaskType()=="Z") {
            if(m.r1!=0 && m.r3!=0){
                m.r1--;
                m.r3--;
                return true;
            }
            else return false;

        }
        return false;
    }
    public static int makeQ(LinkedList<Task> tasks1 ,int sum){
        int size=tasks1.size();
        int num= (int) (0.8 * size);
        int i=0;
        return tasks1.get(num-1).getTaskD();

    }
    public static void main(String[] args) {

        Main m = new Main();

        Scanner scanner = new Scanner(System.in);
        m.typeAl = scanner.nextInt();///1 : fifo  ,2 : sjf  , 3:rrb , 4 : multi queue
        m.r1 = scanner.nextInt();
        m.r2 = scanner.nextInt();
        m.r3 = scanner.nextInt();
        m.nT = scanner.nextInt();
        int n = m.nT;

        int num = 0;
        LinkedList<Task> forQ=new LinkedList<Task>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String type = scanner.next();
            int d = scanner.nextInt();
            num += d;
            Task task = new Task(name, type, d);
            addToReady(task, m.ready, m.typeAl);
            if(m.typeAl==3){
                addToReady(task,forQ, 2);
            }
        }
        int quantom=makeQ(forQ,num);
        int currQ=quantom;
        Task runningT=getfcfs(m.ready);
        switch (runningT.getTaskType()) {
            case "X": {
                m.r1--;
                m.r2--;
                break;
            }
            case "Z": {
                m.r1--;
                m.r3--;
                break;
            }
            case "Y": {
                m.r2--;
                m.r3--;
                break;

            }
        }
        int Ttime=0;
        if(m.typeAl==3) System.out.println("quantom : "+quantom);
        for (int i = 0; i < num; i++) {

            if (m.typeAl == 1) {


                /////checking ready
                Task currTask ;
                if (runningT.getTaskD() <= 0) {

//                    if(m.ready.isEmpty()) {
//                        currTask=getfcfs(m.waiting);
//
////                    }
//                    else {
                    currTask = getfcfs(m.ready);

//                    }
                    System.out.println("arrival time of " + runningT.getTaskName() + " : " + runningT.getTime());
                    runningT = currTask;
                    runningT.setState("running");
                 }
//               } else  if(i!=0){
//                    if(m.ready.isEmpty()) {
//
//
//                    }
//                    else {
//                        currTask = getfcfs(m.ready);
//
//                        currTask.setState("waiting");
//
//                        addToReady(currTask, m.waiting, m.typeAl);
//                    }
//
//                }
                int num1 = runningT.getTaskD();
                num1--;
                runningT.setTaskD(num1);
                System.out.println("using fcfs : \n");
                System.out.println("R1:"+m.r1+"       R2:"+m.r2+"       R3:"+m.r3+"\n");
                System.out.print("Priority queue: [");
                for (int j = 0; j < m.ready.size(); j++) {
                    System.out.print("-"+m.ready.get(j).getTaskName());
                }
                System.out.print("]\n");

                System.out.print("Waiting queue: [");
                for (int j = 0; j < m.waiting.size(); j++) {
                    System.out.print("-"+m.waiting.get(j).getTaskName());
                }
                System.out.println("]\n");
            }
            if(m.typeAl==2){///sjf
                Task currTask ;
                if (runningT.getTaskD() <= 0) {

                    if(m.ready.isEmpty()) {
                        currTask=getfcfs(m.waiting);

                    }
                    else {
                        currTask = getfcfs(m.ready);

                    }
                    System.out.println("arrival time of " + runningT.getTaskName() + " : " + runningT.getTime());
                    runningT =currTask;
                    runningT.setState("running");

                } /*else  if(i!=0){
                    if(m.ready.isEmpty()) {


                    }
                    else {
                        currTask = getfcfs(m.ready);

                        currTask.setState("waiting");

                        addToReady(currTask, m.waiting, m.typeAl);
                    }

                }*/
                int num1 = runningT.getTaskD();
                num1--;
                runningT.setTaskD(num1);
                System.out.println("using sjf : \n");
                System.out.println("R1:"+m.r1+"       R2:"+m.r2+"       R3:"+m.r3+"\n");
                System.out.print("Priority queue: [");
                for (int j = 0; j < m.ready.size(); j++) {
                    System.out.print("-"+m.ready.get(j).getTaskName());
                }
                System.out.print("]\n");

                System.out.print("Waiting queue: [");
                for (int j = 0; j < m.waiting.size(); j++) {
                    System.out.print("-"+m.waiting.get(j).getTaskName());
                }
                System.out.println("]\n");
            }
            if(m.typeAl==3){
                    Task currTask ;
                    if (runningT.getTaskD() <= 0) {///select new task
                        currQ=quantom;
                        switch (runningT.getTaskType()){
                            case "X" :{
                                m.r1++;
                                m.r2++;
                                break;
                            }
                            case "Z" :{
                                m.r1++;
                                m.r3++;
                                break;
                            }
                            case "Y":{
                                m.r2++;
                                m.r3++;
                                break;

                            }
                        }
                        if(m.ready.isEmpty()) {
                            currTask=getfcfs(m.waiting);

                        }
                    else {
                        currTask = getfcfs(m.ready);

                    }

                        System.out.println("arrival time of " + runningT.getTaskName() + " : " + runningT.getTime());
                        runningT = currTask;
                        runningT.setState("running");
                        switch (runningT.getTaskType()) {
                            case "X": {
                                m.r1--;
                                m.r2--;
                                break;
                            }
                            case "Z": {
                                m.r1--;
                                m.r3--;
                                break;
                            }
                            case "Y": {
                                m.r2--;
                                m.r3--;
                                break;

                            }
                        }



                } else if(currQ==0 && runningT.getTaskD()>0){///task hanuz tamum nashode ==> manabe ro bedim behesh
                        currQ=quantom;

                        if(m.ready.isEmpty()) {
                            currTask=getfcfs(m.waiting);

                        }
                        else {
                            currTask = getfcfs(m.ready);

                        }
                        addToReady(runningT,m.ready,m.typeAl);
                        runningT =currTask;
                        runningT.setState("running");
                        boolean rsc=true;
                        switch (currTask.getTaskType()){
                            case "X" :{
                                if(m.r1==0 || m.r2==0)rsc=false;
                                break;
                            }
                            case "Z" :{
                                if(m.r1==0 || m.r3==0)rsc=false;
                                break;
                            }
                            case "Y":{
                                if(m.r3==0 || m.r2==0)rsc=false;
                                break;

                            }
                        }
                        if(rsc) {
                        //    System.out.println("arrival time of " + runningT.getTaskName() + " : " + runningT.getTime());
                            runningT = currTask;
                            runningT.setState("running");
                            switch (runningT.getTaskType()) {
                                case "X": {
                                    m.r1--;
                                    m.r2--;
                                    break;
                                }
                                case "Z": {
                                    m.r1--;
                                    m.r3--;
                                    break;
                                }
                                case "Y": {
                                    m.r2--;
                                    m.r3--;
                                    break;

                                }
                            }
                        }
                        else {
                            System.out.println("adding waiting q");
                            m.waiting.add(currTask);
                        }
                    }
                    else{///quantom > 0

                        currQ--;
/*                    if(m.ready.isEmpty()) {


                    }
                    else {
                        currTask = getfcfs(m.ready);

                        currTask.setState("waiting");

                        addToReady(currTask, m.waiting, m.typeAl);
                    }*/

                }
                int num1 = runningT.getTaskD();
                num1--;
                runningT.setTaskD(num1);
                System.out.println("*****************************");
                System.out.println("using rrb : \n");
                System.out.println("R1:"+m.r1+"       R2:"+m.r2+"       R3:"+m.r3+"\n");
                System.out.print("Priority queue: [");
                for (int j = 0; j < m.ready.size(); j++) {
                    System.out.print("-"+m.ready.get(j).getTaskName());
                }
                System.out.print("]\n");

                System.out.print("Waiting queue: [");
                for (int j = 0; j < m.waiting.size(); j++) {
                    System.out.print("-"+m.waiting.get(j).getTaskName());
                }
                System.out.println("]\n");


            }
            for (int j = 0; j < m.ready.size(); j++) {
                int nu=m.ready.get(j).getTime();
                nu++;
                m.ready.get(j).setTime(nu);
            }
            int nu=runningT.getTime();
            nu++;
            runningT.setTime(nu);


        }

        }
    }




class Task{

    String taskName;
    String taskType;
    int taskD;
    String state;
    int[]resource={0,0,0};
    int arrivalTime=0;

    public int[] getResource() {
        return resource;
    }

    public void setResource(int resource1 , int i , int resource2 , int j) {
        this.resource[i] = resource1;
        this.resource[j] = resource2;
    }

    public Task(String taskName, String taskType, int taskD) {

        this.taskName = taskName;
        this.taskType = taskType;
        this.taskD = taskD;
        state="ready";
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public int getTaskD() {
        return taskD;
    }
    public int getTime() {
        return arrivalTime;
    }

    public String getState() {
        return state;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public void setTaskD(int taskD) {
        this.taskD = taskD;
    }
    public void setTime(int t) {
        this.arrivalTime = t;
    }

    public void setState(String state) {
        this.state = state;
    }
}




/*
import java.util.*;

public class Scheduler {

    private static final int NUM_RESOURCES = 3;
    private static final int NUM_TASKS = 3;

    private List<Task> tasks;
    private List<Task> readyQueue;
    private List<Task> waitingQueue;
    private int[] resources;

    public Scheduler() {
        tasks = new ArrayList<>();
        readyQueue = new ArrayList<>();
        waitingQueue = new ArrayList<>();
        resources = new int[]{0, 2, 1};
    }

    public void readInput() {
        for (int i = 0; i < NUM_TASKS; i++) {
            String[] line = System.console().readLine().split(" ");
            Task task = new Task(line[0], line[1], Integer.parseInt(line[2]));
            tasks.add(task);
        }
    }

    public void schedule() {
        for (int i = 0; i < NUM_TASKS; i++) {
            Task task = tasks.get(i);
            switch (task.getType()) {
                case "Y":
                    if (resources[0] >= 1 && resources[1] >= 1) {
                        readyQueue.add(task);
                        resources[0] -= 1;
                        resources[1] -= 1;
                    } else {
                        waitingQueue.add(task);
                    }
                    break;
                case "X":
                    if (resources[0] >= 1 && resources[2] >= 1) {
                        readyQueue.add(task);
                        resources[0] -= 1;
                        resources[2] -= 1;
                    } else {
                        waitingQueue.add(task);
                    }
                    break;
                case "Z":
                    if (resources[1] >= 1 && resources[2] >= 1) {
                        readyQueue.add(task);
                        resources[1] -= 1;
                        resources[2] -= 1;
                    } else {
                        waitingQueue.add(task);
                    }
                    break;
            }
        }

        while (!readyQueue.isEmpty()) {
            Task task = readyQueue.remove(0);
            System.out.println("Time " + i + ": " + task);
            task.run();
            if (task.isFinished()) {
                resources[task.getType().charAt(0) - 'A'] += 1;
            } else {
                readyQueue.add(task);
            }
        }
    }

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.readInput();
        scheduler.schedule();
    }
}

class Task {

    private String name;
    private String type;
    private int duration;
    private int timeRun;
    private boolean finished;

    public Task(String name, String type, int duration) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.timeRun = 0;
        this.finished = false;
    }

    public void run() {
        timeRun++;
        if (timeRun == duration) {
            finished = true;
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public String toString() {
        return name + " " + type + " " + timeRun;
    }
}



*/
