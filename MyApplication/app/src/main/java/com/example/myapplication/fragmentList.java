package com.example.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
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

public class fragmentList extends Fragment implements View.OnClickListener{




    @Override
    public void onClick(View v) {

        itemRep.add();
        mAdapter.notifyDataSetChanged();
    }

    public interface IListener{

        public void onClicked(item item);

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
        mAdapter=new Adapter(itemRep.getInstance().list(), new ClickChecker());
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

    class ClickChecker implements ViewHolder.IListener{
        @Override
        public void onClicked(int position){
            final item item = itemRep.getInstance().item(position);

            if (mListener != null) {
                mListener.onClicked(item);
            }
        }
    }
}
