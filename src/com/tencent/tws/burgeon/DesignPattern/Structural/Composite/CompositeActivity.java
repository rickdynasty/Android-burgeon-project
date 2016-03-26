 package com.tencent.tws.burgeon.DesignPattern.Structural.Composite;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tencent.tws.devicemanager.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class CompositeActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Composite_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        Company root = new ConcreteCompany();
        root.setName("Beijing Inc.Co");
        root.add(new HRDepartment("Inc.Co HRD"));//human resources department
        root.add(new FinanceDepartment("Inc.Co FD"));//Financial department

        Company shandongCom = new ConcreteCompany("shandong branch");
        shandongCom.add(new HRDepartment("shandong  HRD"));
        shandongCom.add(new FinanceDepartment("shandong FD"));

        Company zaozhuangCom = new ConcreteCompany("Zaozhuang office");
        zaozhuangCom.add(new FinanceDepartment("Zaozhuang FD"));
        zaozhuangCom.add(new HRDepartment("Zaozhuang HRD"));
        Company jinanCom = new ConcreteCompany("jinan Division");
        jinanCom.add(new FinanceDepartment("jinan FD"));
        jinanCom.add(new HRDepartment("jinan HRD"));
        shandongCom.add(jinanCom);
        shandongCom.add(zaozhuangCom);

        Company huadongCom = new ConcreteCompany("Shanghai east China branch");
        huadongCom.add(new HRDepartment("Shanghai HRD"));
        huadongCom.add(new FinanceDepartment("Shanghai FD"));

        root.add(shandongCom);
        root.add(huadongCom);
        root.display(0);
    }
}
