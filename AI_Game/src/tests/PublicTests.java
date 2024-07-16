package tests;


import code.UnitedWeStandSearch;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PublicTests {

    String grid0 = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0;";
    String grid1 = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0,1,1;";
    String grid2 = "9;7;4,2,6,5,5,6,1,5,6,1,8,1,8,2,4,3,6,0,7,5;0,1,0,2,0,3,0,5,1,4,6,3,6,6,2,0,0,4,3,3,8,4,3,0,8,6,5,4,5,1,0,0,3,2,8,0,2,2,6,2,7,3,5,2,5,3,2,6,4,6,0,6,1,6,1,2,1,3,8,3;"; // removed the obstacle at 2, 7
    String grid3 = "8;6;2,1,4,3,4,2,2,0,4,4,5,2,2,3,2,4,5,5,4,5,2,2;6,4,6,2,7,5,7,2,6,3,3,0,3,5,7,0,3,1,3,3,5,1;";
    String grid4 = "6;6;2,1,2,0;0,5,0,2,3,0,3,5,0,0,3,1,3,3,5,1;";

    @Test(timeout = 120000)
    public void testa0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "BF", false);
solution = solution.replace(" ", "");
        System.out.println(solution);
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }
    @Test(timeout = 120000)
    public void testa1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "BF", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }
    @Test(timeout = 120000)
    public void testa2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "BF", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid2, solution));
    }
    @Test(timeout = 120000)
    public void testa3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "BF", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void testa4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "BF", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }

    @Test(timeout = 120000)
    public void testb0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "DF", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }

    @Test(timeout = 120000)
    public void testb1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "DF", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }

    @Test(timeout = 120000)
    public void testb2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "DF", false);
        solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.",  pc.applyPlan(grid2, solution));
    }

    @Test(timeout = 120000)
    public void testb3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "DF", false);
solution = solution.replace(" ", "");
        System.out.println(solution);
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.",pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void testb4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "DF", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }


    @Test(timeout = 120000)
    public void testc0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "UC", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }
    @Test(timeout = 120000)
    public void testc1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "UC", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }

    @Test(timeout = 120000)
    public void testc2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "UC", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid2, solution));
    }

    @Test(timeout = 120000)
    public void testc3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "UC", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void testc4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "UC", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }

    @Test(timeout = 120000)
    public void testd0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "ID", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }

    @Test(timeout = 120000)
    public void testd1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "ID", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }

    @Test(timeout = 120000)
    public void testd2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "ID", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid2, solution));
    }

    @Test(timeout = 120000)
    public void testd3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "ID", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void testd4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "ID", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }


    @Test(timeout = 120000)
    public void teste0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "GR1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }

    @Test(timeout = 120000)
    public void teste1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "GR1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }

    @Test(timeout = 120000)
    public void teste2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "GR1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid2, solution));
    }

    @Test(timeout = 120000)
    public void teste3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "GR1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void teste4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "GR1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }


    @Test(timeout = 120000)
    public void testf0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "GR2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }

    @Test(timeout = 120000)
    public void testf1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "GR2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }

    @Test(timeout = 120000)
    public void testf2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "GR2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid2, solution));
    }

    @Test(timeout = 120000)
    public void testf3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "GR2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void testf4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "GR2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }

    @Test(timeout = 120000)
    public void testg0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "AS1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }

    @Test(timeout = 120000)
    public void testg1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "AS1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }

    @Test(timeout = 120000)
    public void testg2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "AS1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid2, solution));
    }

    @Test(timeout = 120000)
    public void testg3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "AS1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void testg4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "AS1", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }


    @Test(timeout = 120000)
    public void testh0() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid0, "AS2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid0);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid0, solution));
    }

    @Test(timeout = 120000)
    public void testh1() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid1, "AS2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid1);
        assertTrue("The output actions do not lead to a goal state.", solution.toLowerCase().replaceAll(" ", "").equals("nosolution"));
    }

    @Test(timeout = 120000)
    public void testh2() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid2, "AS2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid2);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid2, solution));
    }

    @Test(timeout = 120000)
    public void testh3() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid3, "AS2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid3);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid3, solution));
    }

    @Test(timeout = 120000)
    public void testh4() throws Exception {
        String solution = UnitedWeStandSearch.solve(grid4, "AS2", false);
solution = solution.replace(" ", "");
        Checker pc = new Checker(grid4);
        assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(grid4, solution));
    }


}

    class Checker{
    private HashMap<String, Integer> gc = new HashMap<String, Integer>();
    private ArrayList<String> sc =new ArrayList<String>();
    private int v2;
    private int v1;
    int v0 = 0;
    Checker(String description){
        String [] sections = description.split(";");
        this.v1 =Integer.parseInt(sections[0]);
        this.v2 =Integer.parseInt(sections[1]);
        String [] s2 = sections[2].split(",");
        for (int i=0; i<s2.length;i+=2 ) this.gc.put(Integer.parseInt(s2[i])+","+Integer.parseInt(s2[i+1]), 1);
        String [] s3 = sections[3].split(",");
        for (int i=0; i<s3.length;i+=2 )  this.sc.add(Integer.parseInt(s3[i])+","+Integer.parseInt(s3[i+1]));
            }
    public boolean tryPlan(String[] actions, Checker s) {
        boolean linkin = false;
        for (int i = 0; i < actions.length; i++) {

            linkin =s.doAction(actions[i]);

            if(!linkin) {
                System.out.println("action that failed: "+actions[i] +", order: "+i);
                return false;
            }
        }
        return true;
    }
    int a(String dir){
        switch (dir){
            case "east" : {return 1;}
            case "west" : {return -1;}
            default : {return 0;}
        }
    }
    int b(String dir){
        switch (dir){
            case "north" : {return -1;}
            case "south" : {return 1;}
            default : {return 0;}
        }
    }
    String fc(String d, int a1, int b1){
        int da= a(d); int db= b(d);
        int a2=a1; int b2=b1;

        while (true){
            a2+=da; b2+=db;
            if (sc.contains(a2+","+b2)){
                return "s,"+(a2-da)+","+(b2-db);
            }
            if (gc.containsKey(a2+","+b2)){
                return "g,"+a2+","+b2;
            }
            if(a2<0 || a2> v1 || b2<0||b2> v2){
                return "f,-1,-1";
            }

        }
    }


    void mg(int a1, int b1, int a2, int b2){
        String one = a1+","+b1;
        String two = a2+","+b2;
        this.v0 +=this.gc.get(one) * (Math.abs(a1-a2)+ Math.abs(b1-b2));
        this.gc.put(two, (this.gc.get(one)+this.gc.get(two)));
        this.gc.remove(one);
    }
    void sa(int a1, int b1, int a2, int b2) {
        String one = a1 + "," + b1;
        String two = a2 + "," + b2;
        this.v0 += this.gc.get(one) * (Math.abs(a1 - a2) + Math.abs(b1 - b2));
        this.gc.put(two, this.gc.get(one));
        this.gc.remove(one);
    }
    boolean doAction(String act){
        String [] acts = act.split("_");
        String [] c = fc(acts[0], Integer.parseInt(acts[1]), Integer.parseInt(acts[2])).split(",");
        int a2 = Integer.parseInt(c[1]);
        int b2= Integer.parseInt(c[2]);

        if  (c[0].equals("g")){
            mg(Integer.parseInt(acts[1]), Integer.parseInt(acts[2]), a2, b2);
            return true;
        }
        if(c[0].equals("s")){
            sa(Integer.parseInt(acts[1]), Integer.parseInt(acts[2]), a2, b2);
            return true;
        }

        return false;
    }

    boolean cool(){
        return this.gc.size()== 1;
    }
    public boolean applyPlan(String grid, String solution){
        boolean linkin = true;
        solution = solution.toLowerCase();
        if (solution.equals("nosolution")) {
            return false;
        }
//    System.out.println(solution);
        String[] solutionArray  = solution.split(";");
        String plan = solutionArray[0];
        int blue = Integer.parseInt(solutionArray[1]);
        plan.replace(" ", "");
        plan.replace("\n", "");
        plan.replace("\r", "");
        plan.replace("\n\r", "");
        plan.replace("\t", "");

        String[] actions = plan.split(",");

        Checker s = new Checker(grid);
        linkin = tryPlan(actions,s);
        if(!linkin) {
            return false;
        }
        return s.cool()    && s.v0 ==blue;
    }
}

