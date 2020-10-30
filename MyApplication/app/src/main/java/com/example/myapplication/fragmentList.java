package com.example.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentList extends Fragment implements View.OnClickListener{

    protected static final String FL_TAG = "FL_TAG";


    @Override
    public void onClick(View v) {

        int position = ItemRep.add();
        mAdapter.notifyItemInserted(position);
    }

    public interface IListener{

        public void onClicked(Item item);

    }

    private Adapter mAdapter;
    protected IListener mListener;
    protected Button button;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (requireActivity() instanceof IListener) {
            mListener = (IListener) requireActivity();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(this);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recycler = view.findViewById(R.id.recycler);

        GridLayoutManager mLayout = new GridLayoutManager(getActivity(), getScreenOrientation(), LinearLayoutManager.VERTICAL, false);
        if (savedInstanceState!=null){
            int amount = savedInstanceState.getInt(FL_TAG);
            ItemRep.setInstance(amount);
        }
        mAdapter=new Adapter(ItemRep.getInstance().list(), new ClickChecker());
        recycler.setAdapter(mAdapter);
        recycler.setLayoutManager(mLayout);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    private int getScreenOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return 3;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return 4;
        else
            return 3;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(FL_TAG, ItemRep.getInstance().size());
    }

    class ClickChecker implements ViewHolder.IListener{
        @Override
        public void onClicked(int position){
            final Item item = ItemRep.getInstance().item(position);

            if (mListener != null) {
                mListener.onClicked(item);
            }
        }
    }
}
