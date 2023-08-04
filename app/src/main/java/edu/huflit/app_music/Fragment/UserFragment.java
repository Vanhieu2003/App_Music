package edu.huflit.app_music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import edu.huflit.app_music.Activity.LoginActivity;
import edu.huflit.app_music.R;


public class UserFragment extends Fragment {

    private TextView tvUserName;
    private boolean isLogin;
    private String userName;
    private androidx.appcompat.widget.AppCompatButton btn_logout_user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        // Ánh xạ các view tại đây

        tvUserName=rootView.findViewById(R.id.tv_user);
        btn_logout_user=rootView.findViewById(R.id.btn_logout_user);


        // Lấy dữ liệu từ Bundle
        Bundle args = getArguments();
        if (args != null) {
            isLogin = args.getBoolean("islogin", false);
            userName = args.getString("userName", "");
        }

        // Hiển thị nội dung dựa trên dữ liệu từ Bundle
        if (isLogin) {
            tvUserName.setText("Xin chào, " + userName);
            // Hiển thị nút Đăng xuất và xử lý sự kiện khi người dùng nhấn vào nó
            btn_logout_user.setVisibility(View.VISIBLE);
            btn_logout_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Thực hiện đăng xuất và chuyển trở lại LoginActivity
                    performLogout();
                }
            });
        } else {
            tvUserName.setText("Bạn chưa đăng nhập");
            btn_logout_user.setVisibility(View.GONE);
        }

        return rootView;
    }

    private void performLogout() {
        // Thực hiện đăng xuất
        isLogin = false;
        userName = "";

        // Chuyển trở lại LoginActivity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}