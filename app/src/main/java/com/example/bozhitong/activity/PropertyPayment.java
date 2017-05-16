package com.example.bozhitong.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bozhitong.R;
import com.example.bozhitong.dialog.view.SelectPicPopupWindow;
import com.example.bozhitong.entity.GroupInfo;
import com.example.bozhitong.entity.ProductInfo;
import com.example.bozhitong.fragment.adapter.ShopcartExpandableListViewAdapter;
import com.example.bozhitong.fragment.adapter.ShopcartExpandableListViewAdapter.CheckInterface;
import com.example.bozhitong.fragment.adapter.ShopcartExpandableListViewAdapter.ModifyCountInterface;
import com.example.bozhitong.time.TimePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物业缴费 lirui 2017s
 *
 * @author 12306
 */
public class PropertyPayment extends Activity implements CheckInterface,
        ModifyCountInterface, OnClickListener {
    private ExpandableListView exListView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;
    private Context context;
    private ImageView mBack;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private TextView mStartDate, mClosingDate;
    private int statrdate = 0;
    private int closingdate = 0;

    private ShopcartExpandableListViewAdapter selva;
    private List<GroupInfo> groups = new ArrayList<GroupInfo>();// 组元素数据列表
    private Map<String, List<ProductInfo>> children = new HashMap<String, List<ProductInfo>>();// 子元素数据列表
    SelectPicPopupWindow menuWindow;
private TimePopupWindow timePopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_propertypayment);

        initView();
        initEvents();
    }

    private void initView() {
        context = this;
        virtualData();
        exListView = (ExpandableListView) findViewById(R.id.exListView);
        cb_check_all = (CheckBox) findViewById(R.id.all_chekbox);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        // tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) findViewById(R.id.tv_go_to_pay);
        mBack = (ImageView) findViewById(R.id.propert_iv);
        mBack.setOnClickListener(this);
        mStartDate = (TextView) findViewById(R.id.start_date);
        mStartDate.setOnClickListener(this);
        mClosingDate = (TextView) findViewById(R.id.closing_date);
        mClosingDate.setOnClickListener(this);

        timePopupWindow = new TimePopupWindow(this);
    }

    private void initEvents() {
        selva = new ShopcartExpandableListViewAdapter(groups, children, this);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(selva);

        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }

        cb_check_all.setOnClickListener(this);
        // tv_delete.setOnClickListener(this);
        tv_go_to_pay.setOnClickListener(this);
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void virtualData() {

        for (int i = 0; i < 1; i++) {

            groups.add(new GroupInfo(i + "", "房号：高科尚都摩卡一单元2栋209"));

            List<ProductInfo> products = new ArrayList<ProductInfo>();
            String[] strArray = {"物业费", "垃圾费", "电费", "水费", "电梯费", "车位费",
                    "天然气费", "卫生费", "历史欠费"};
            for (int j = 0; j < strArray.length; j++) {
                if (j == strArray.length - 1) {
                    products.add(new ProductInfo(j + "", "商品", "", strArray[j],
                            360.00, 1));
                } else {
                    products.add(new ProductInfo(j + "", "商品", "", strArray[j],
                            120.00, 1));
                }

            }
            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
        }
    }

    @Override
    public void onClick(View v) {
        AlertDialog alert;
        switch (v.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.propert_iv:
                finish();
                break;
            case R.id.start_date:// 开始日期
//                new DatePickerDialog(PropertyPayment.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//                                mStartDate.setText(String.format("%d-%d-%d", year,
//                                        monthOfYear + 1, dayOfMonth));
//                                statrdate = monthOfYear + 1;
//                            }
//                        }, 2000, 1, 2).show();
                statrdate =timePopupWindow.showBottoPopupWindow("选择时间",mStartDate);
                break;
            case R.id.closing_date:// 结束日期
                closingdate = timePopupWindow.showBottoPopupWindow("选择时间",mClosingDate);
//			new DatePickerDialog(PropertyPayment.this,
//					new DatePickerDialog.OnDateSetListener() {
//						@Override
//						public void onDateSet(DatePicker view, int year,
//								int monthOfYear, int dayOfMonth) {
//							mClosingDate.setText(String.format("%d-%d-%d",
//									year, monthOfYear + 1, dayOfMonth));
//							closingdate = monthOfYear + 1;
//						}
//					}, 2000, 1, 2).show();

                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择合计，进行支付缴费", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                if ((closingdate - statrdate) == 0) {
                    alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice
                            + "元");
                } else {
                    alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice
                            * (closingdate - statrdate) + "元");
                }

                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                menuWindow = new SelectPicPopupWindow(
                                        PropertyPayment.this, itemsOnClick);

                                menuWindow.showAtLocation(PropertyPayment.this
                                                .findViewById(R.id.propetypay),
                                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                                        0, 0);
                                dialog.dismiss();
                                return;
                            }
                        });
                alert.show();
                break;
            // case R.id.tv_delete:
            // if (totalCount == 0) {
            // Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
            // return;
            // }
            // alert = new AlertDialog.Builder(context).create();
            // alert.setTitle("操作提示");
            // alert.setMessage("您确定要将这些商品从购物车中移除吗？");
            // alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
            // new DialogInterface.OnClickListener() {
            // @Override
            // public void onClick(DialogInterface dialog, int which) {
            // return;
            // }
            // });
            // alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
            // new DialogInterface.OnClickListener() {
            // @Override
            // public void onClick(DialogInterface dialog, int which) {
            // doDelete();
            // }
            // });
            // alert.show();
            // break;
        }
    }

    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        List<GroupInfo> toBeDeleteGroups = new ArrayList<GroupInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            if (group.isChoosed()) {

                toBeDeleteGroups.add(group);
            }
            List<ProductInfo> toBeDeleteProducts = new ArrayList<ProductInfo>();// 待删除的子元素列表
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteProducts);
        }
        groups.removeAll(toBeDeleteGroups);

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {

        ProductInfo product = (ProductInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {

        ProductInfo product = (ProductInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;

        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck()) {
            cb_check_all.setChecked(true);
        } else {
            cb_check_all.setChecked(false);
        }

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosiTion,
                           boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            cb_check_all.setChecked(true);
        } else {
            cb_check_all.setChecked(false);
        }

        selva.notifyDataSetChanged();
        calculate();
    }

    private boolean isAllCheck() {

        for (GroupInfo group : groups) {
            if (!group.isChoosed())
                return false;

        }

        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(cb_check_all.isChecked());
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(cb_check_all.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                ProductInfo product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getCount();
                }
            }
        }
        if ((closingdate - statrdate) == 0) {
            tv_total_price.setText("￥" + totalPrice);
            tv_go_to_pay.setText("去支付(" + totalCount + ")");
        } else {
            tv_total_price
                    .setText("￥" + totalPrice * (closingdate - statrdate));
            tv_go_to_pay.setText("去支付(" + totalCount + ")");
        }

    }

    private OnClickListener itemsOnClick = new OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_paybyalipay:
                    break;
                case R.id.btn_wechatpay:
                    break;
                default:
                    break;
            }

        }

    };



}
