package com.fullstack.manuel.minesweeper;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TableRow.LayoutParams params;
    TableLayout.LayoutParams paramsTable;

    int [][]minesMat = new int[][]{
            { 1, 0, 0, 0, 0, 0, 0, 0, 1},
            { 0, 1, 0, 0, 0, 0, 0, 1, 0},
            { 0, 0, 1, 0, 0, 0, 1, 0, 0},
            { 0, 0, 0, 1, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 1, 0, 0, 0},
            { 0, 1, 0, 0, 0, 0, 1, 0, 0},
            { 1, 0, 0, 0, 0, 0, 0, 1, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 1}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout Params
        params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        paramsTable = new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        paramsTable.weight = 1;



        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout);
        for (int i = 0;i<9;i++) {
            TableRow row = new TableRow(this);
            CreateBlocks(row,i);
            row.setLayoutParams(paramsTable);
            tableLayout.addView(row);
        }

    }

    void CreateBlocks(TableRow row,int numRow){
        for(int i= 0;i<9;i++){
            ImageButton cell = new ImageButton(this);
            cell.setBackgroundResource(R.drawable.full);
            cell.setLayoutParams(params);
            cell.setTag(String.valueOf(i)+","+String.valueOf(numRow));

            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentRow = Integer.parseInt(""+v.getTag().toString().charAt(0));
                    int currentColumn = Integer.parseInt(""+v.getTag().toString().charAt(2));
                    //Toast.makeText(getApplicationContext(), ""+v.getTag(), Toast.LENGTH_SHORT).show();
                    if(minesMat[currentRow][currentColumn]==0){
                        minesMat[currentRow][currentColumn]=2;
                        if(CheckNeighborValue(currentRow,currentColumn)==0){
                            v.setBackgroundResource(R.drawable.baseblock);
                        }else if(CheckNeighborValue(currentRow,currentColumn)==1){
                            v.setBackgroundResource(R.drawable.uno);
                        }else if(CheckNeighborValue(currentRow,currentColumn)==2){
                            v.setBackgroundResource(R.drawable.dos);
                        }else if(CheckNeighborValue(currentRow,currentColumn)==3){
                            v.setBackgroundResource(R.drawable.tres);
                        }else{
                            v.setBackgroundResource(R.drawable.cuatro);
                        }
                    }else if(minesMat[currentRow][currentColumn]==1){
                        v.setBackgroundResource(R.drawable.mineblock);
                    }

                    Log.d("this",String.valueOf(currentRow)+String.valueOf(currentColumn));
                }
            });
            row.addView(cell);
        }
    }

    int CheckNeighborValue(int i,int j){
        int count = 0;
        try { if (minesMat[i-1][j-1] ==1) count++; }catch (Exception e){}
        try { if (minesMat[i-1][j] ==1) count++; }catch (Exception e){}
        try { if (minesMat[i-1][j+1] ==1) count++; }catch (Exception e){}

        try { if (minesMat[i][j-1] ==1) count++; }catch (Exception e){}
        try { if (minesMat[i][j+1] ==1) count++; }catch (Exception e){}

        try { if (minesMat[i+1][j-1] ==1) count++; }catch (Exception e){}
        try { if (minesMat[i+1][j] ==1) count++; }catch (Exception e){}
        try { if (minesMat[i+1][j+1] ==1) count++; }catch (Exception e){}



        return count;
    }
}
