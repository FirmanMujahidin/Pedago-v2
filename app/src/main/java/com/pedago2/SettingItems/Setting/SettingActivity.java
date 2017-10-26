package com.pedago2.SettingItems.Setting;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pedago2.Base.BasesActivity;
import com.pedago2.R;

import butterknife.BindView;

public class SettingActivity extends BasesActivity {

    @BindView(R.id.tv_edit_profile)
    TextView tvEditProfile;
    @BindView(R.id.ll_edit_profile)
    LinearLayout llEditProfile;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    @BindView(R.id.ll_change_password)
    LinearLayout llChangePassword;
    @BindView(R.id.tv_posts_like)
    TextView tvPostsLike;
    @BindView(R.id.ll_posts_like)
    LinearLayout llPostsLike;
    @BindView(R.id.tv_private_account)
    TextView tvPrivateAccount;
    @BindView(R.id.swt_private_account)
    SwitchCompat swtPrivateAccount;
    @BindView(R.id.ll_private_account)
    LinearLayout llPrivateAccount;
    @BindView(R.id.tv_linked_account)
    TextView tvLinkedAccount;
    @BindView(R.id.ll_linked_account)
    LinearLayout llLinkedAccount;
    @BindView(R.id.tv_push)
    TextView tvPush;
    @BindView(R.id.ll_Push)
    LinearLayout llPush;
    @BindView(R.id.tv_save_photos)
    TextView tvSavePhotos;
    @BindView(R.id.ll_save_photo)
    LinearLayout llSavePhoto;
    @BindView(R.id.tv_preload_videos)
    TextView tvPreloadVideos;
    @BindView(R.id.tv_only_wifi)
    TextView tvOnlyWifi;
    @BindView(R.id.ll_preload_video)
    LinearLayout llPreloadVideo;
    @BindView(R.id.tv_help_center)
    TextView tvHelpCenter;
    @BindView(R.id.ll_help_center)
    LinearLayout llHelpCenter;
    @BindView(R.id.tv_report_problem)
    TextView tvReportProblem;
    @BindView(R.id.ll_report_problem)
    LinearLayout llReportProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_setting);
        initToolbar("PENGATURAN", true);


    }
}
