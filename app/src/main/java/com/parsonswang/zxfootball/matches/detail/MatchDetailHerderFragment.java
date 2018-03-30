package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseFragment;
import com.parsonswang.common.image.Imageloaders;
import com.parsonswang.common.view.MarqueTextView;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.view.MatchScoreInfoView;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

/**比赛头部信息Fragment
 * Created by parsonswang on 2018/2/6.
 */
public class MatchDetailHerderFragment extends BaseFragment implements MatchContract.IMatchDetailView, MatchContract.IMatchStatView {

    private MatchScoreInfoView<MatchesBean.MatchInfo> matchInfoMatchScoreInfoView;
    private MarqueTextView mMarqueTextView;
    private MatchDetailHeaderInfoBean matchDetailHeaderInfoBean;
    private MatchPresenter mMatchPresenter;

    public static MatchDetailHerderFragment newInstance(String matchId) {
        MatchDetailHerderFragment matchDetailHerderFragment = new  MatchDetailHerderFragment();
        Bundle data = new Bundle();
        data.putString("matchId", matchId);
        matchDetailHerderFragment.setArguments(data);
        return matchDetailHerderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchdetail_header, container, false);

        matchInfoMatchScoreInfoView = view.findViewById(R.id.match_score_view);
        matchInfoMatchScoreInfoView.mRootLayput.setBackgroundResource(R.color.colorCommonBackground);
        mMarqueTextView = view.findViewById(R.id.iv_match_summary);

        mMatchPresenter = new MatchPresenter(this, this);
        final String matchId = getArguments().getString("matchId");
        mMatchPresenter.getMatchDetail(matchId);
        mMatchPresenter.getMatchStat(matchId);

        return view;
    }

    @Override
    public void showExceptionView() {

    }

    @Override
    public void showMatchInfoHeader(MatchDetailHeaderInfoBean matchDetailHeaderInfoBean) {
        this.matchDetailHeaderInfoBean = matchDetailHeaderInfoBean;

        Imageloaders.loadImage(mContext, matchDetailHeaderInfoBean.homeTeamLogo, matchInfoMatchScoreInfoView.mHomeTeam.mIvTeam, 0);
        matchInfoMatchScoreInfoView.mHomeTeam.mTvTeam.setText(matchDetailHeaderInfoBean.homeTeamName);

        Imageloaders.loadImage(mContext, matchDetailHeaderInfoBean.awayTeamLogo, matchInfoMatchScoreInfoView.mAwayTeam.mIvTeam, 0);
        matchInfoMatchScoreInfoView.mAwayTeam.mTvTeam.setText(matchDetailHeaderInfoBean.awayTeamName);
        matchInfoMatchScoreInfoView.mTvScore.setText(matchDetailHeaderInfoBean.endTimeScore);
    }

    @Override
    public void showMatchSummary(MatchSummary matchSummary) {
        mMarqueTextView.setText(matchDetailHeaderInfoBean.homeTeamName + ":" + matchSummary.getHomeMatchSummary() + " " + matchDetailHeaderInfoBean.awayTeamName + ":"  + matchSummary.getAwayMatchSummary());
    }

    @Override
    public void getGoalPlayersInfo(GoalPlayers goalPlayers) {

    }
}
