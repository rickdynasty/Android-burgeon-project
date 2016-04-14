package com.tencent.tws.core.apidemos.widget.dialogfragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.tencent.tws.core.app.AlertDialog;
import com.tencent.tws.core.app.ProgressDialog;
import com.tencent.tws.core.app.TwsDialog;
import com.tencent.tws.core.support.v4.app.TwsFragmentActivity;
import com.tencent.tws.core.widget.AdapterView;
import com.tencent.tws.core.widget.AdapterView.OnItemClickListener;
import com.tencent.tws.core.widget.ListView;
import com.tencent.tws.core.widget.Toast;
import com.tencent.tws.burgeon.R;

public class DialogFragmentActivity extends TwsFragmentActivity {

    private Button showDialogButton;
    private final int DIALOG_ID = 1;
    ListView listView;

    protected List<Map<String, String>> getData() {

        List<Map<String, String>> myData = new ArrayList<Map<String, String>>();
        for (int i = 0; i < itemList.length; i++) {

            Map<String, String> entries = new HashMap<String, String>();
            entries.put("tittle", itemList[i]);
            myData.add(entries);
        }
        return myData;
    }

    String[] itemList = new String[] { "DialogFragment", "DialogActivity", "Normal_AlertDialog", "Radio_AlertDialog","3Button_AlertDialog","list_AlertDialog",
            "progress_AlertDialog","Muticheck_AlertDialog","Spinner_AlertDialog"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialogfragment_widget);
        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new SimpleAdapter(DialogFragmentActivity.this, getData(), android.R.layout.simple_list_item_1, new String[] { "tittle" },
                new int[] { android.R.id.text1 }));
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                case 0:
                    MyDialogFragment fragment = MyDialogFragment.newInstance("DialogFragment Title");
                    fragment.show(getSupportFragmentManager(), "dialog");
                    break;
                case 1:
                    DialogFragmentActivity.this.showTwsDialog(DIALOG_ID);
                    break;
                case 2:
                    new AlertDialog.Builder(DialogFragmentActivity.this).setTitle("AlertDialog Title").setMessage("AlertDialog Content")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(DialogFragmentActivity.this, "ok is pressed", Toast.LENGTH_SHORT).show();
                                }
                            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(DialogFragmentActivity.this, "cancel is pressed", Toast.LENGTH_SHORT).show();
                                }
                            }).create().show();
                    break;
                case 3:
                    new AlertDialog.Builder(DialogFragmentActivity.this).setSingleChoiceItems(new String[] { "Item1", "Item2", "Item3" }, 0, new DialogInterface.OnClickListener() {
                        
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(DialogFragmentActivity.this, "item "+which+" is pressed", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    break;
                case 4:  
                    new AlertDialog.Builder(DialogFragmentActivity.this).setIcon(R.drawable.ic_burgeon)
                    .setTitle("ͶƱ")
                    .setMessage("����Ϊʲô���������������")
                    .setPositiveButton("��Ȥζ��", new DialogInterface.OnClickListener() {  
                        public void onClick(DialogInterface dialog, int whichButton) {  
                            Toast.makeText(DialogFragmentActivity.this, "��ѡ������Ȥζ��", Toast.LENGTH_SHORT).show();
                        }  
                    })
                    .setNeutralButton("��˼���", new DialogInterface.OnClickListener() {  
                        public void onClick(DialogInterface dialog, int whichButton) {  
                            Toast.makeText(DialogFragmentActivity.this, "��ѡ������˼���", Toast.LENGTH_SHORT).show();
                        }  
                    })
                    .setNegativeButton("����ǿ��", new DialogInterface.OnClickListener() {  
                        public void onClick(DialogInterface dialog, int whichButton) {  
                            Toast.makeText(DialogFragmentActivity.this, "��ѡ��������ǿ��", Toast.LENGTH_SHORT).show();
                        }  
                    })
                    .create().show(); 
                    break;
                case 5:
                    final String[] mItems = {"item0","item1","itme2","item3","itme4","item5","item6"}; 
                    new AlertDialog.Builder(DialogFragmentActivity.this).setTitle("�б�ѡ���")
                    .setItems(mItems, new DialogInterface.OnClickListener() {  
                                public void onClick(DialogInterface dialog, int which) {
                                }  
                            }).create().show();  
                    break;
                case 6:
                    ProgressDialog mProgressDialog = new ProgressDialog(DialogFragmentActivity.this);  
                    mProgressDialog.setIcon(R.drawable.ic_burgeon);  
                    mProgressDialog.setTitle("���������"); 
                    mProgressDialog.setProgress(50);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mProgressDialog.setButton("ȷ��", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            
                        }
                    });  
                    mProgressDialog.setButton2("ȡ��", new DialogInterface.OnClickListener() {  
                        public void onClick(DialogInterface dialog, int whichButton) {  
                            //������ӵ������߼�  
                        }  
                    });  
                    mProgressDialog.show(); 
                    mProgressDialog.incrementProgressBy(20);
                    break;
                case 7:
                    final String[] mItems2 = {"item0","item1","itme2","item3","itme4","item5","item6"}; 
                    new AlertDialog.Builder(DialogFragmentActivity.this).setTitle("�б�ѡ���")
                    .setMultiChoiceItems(mItems2, null, null).create().show();  
                    break;
                case 8:
                    ProgressDialog mProgressDialog2 = new ProgressDialog(DialogFragmentActivity.this);  
                    mProgressDialog2.setTitle("��ȡing");  
                    mProgressDialog2.setMessage("���ڶ�ȡ�����Ժ�");  
                    mProgressDialog2.setIndeterminate(true);  
                    mProgressDialog2.setCancelable(true);  
                    mProgressDialog2.show(); 
                    break;
                }

            }
        });

    }

    @Override
    protected TwsDialog onCreateTwsDialog(int id) {
        // TODO Auto-generated method stub
        String title = this.getString(R.string.app_name);
        return new AlertDialog.Builder(this).setTitle(title).setMessage(title).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        }).create();
    }

}
