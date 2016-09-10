package com.xpc.learnrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xpc.learnrecyclerview.R;

import java.util.List;
import java.util.Random;

/**
 * Created by xiepc on 2016/9/10 0010 13:56
 */
public class MyRecyclerAdater extends RecyclerView.Adapter{

    private Context context;
    private int[] arrayInt = new int[30];
    List<String> datas;
    OnClickPerform onClickPerform;
    public MyRecyclerAdater(Context context, List<String> datas){
        this.context = context;
        this.datas = datas;
        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i] =100+ new Random().nextInt(300);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,null);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TextView tv = ((ViewHoler)holder).getTextView();
        tv.setText(datas.get(position));
        ViewGroup.LayoutParams param =  tv.getLayoutParams();
        param.height = arrayInt[position];
        tv.setLayoutParams(param);
        setItemClick(((ViewHoler)holder));//传布局位置防止添加item时，位置重复
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addItem(int pos){
        datas.add(pos,"这是新加的item");
        notifyItemInserted(pos);
    }
    public void removeItem(int pos){
        datas.remove(pos);
        notifyItemRemoved(pos);
    }
   class ViewHoler extends RecyclerView.ViewHolder{
       private TextView tv;
       private View itemView;
       public ViewHoler(View itemView) {
           super(itemView);
           this.itemView = itemView;
           tv = (TextView) itemView.findViewById(R.id.contentText);
       }
       public TextView getTextView(){
           return tv;
       }

       public View getItemView(){
           return itemView;
       }
   }
    public void setOnClickPerform(OnClickPerform onClickPerform){
        this.onClickPerform = onClickPerform;
    }
    /**在BindView中设置点击监听*/
    protected void setItemClick(final ViewHoler holder){
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickPerform != null){
                    onClickPerform.onItemClick(holder.getItemView(),holder.getLayoutPosition());
                }
            }
        });
        holder.getItemView().setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(onClickPerform != null){
                    onClickPerform.onItemLongClick(holder.getItemView(),holder.getLayoutPosition());
                }
                return false;
            }
        });
    }
    /**点击响应接口*/
    public interface OnClickPerform{
        public void onItemClick(View view,int position);
        public void onItemLongClick(View view,int position);
    }
}
