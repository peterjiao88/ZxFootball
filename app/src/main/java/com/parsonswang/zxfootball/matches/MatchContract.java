package com.parsonswang.zxfootball.matches;

import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchStatBean;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.mvp.IBaseView;

/**
 * Created by wangchun on 2017/10/22.
 */

public class MatchContract {

    public interface IMatchView {
         void showHeaderTabTitle(HeaderTabTitle headerTabTitle);
    }

    public interface IMatchInfoView {
         void showMatchInfoList(MatchesBean matchesBean);
    }


    public interface IMatchPresenter {

        void getMatchInfos(String comeptitionId, String dataBetween);

        void getMatchDetail(String matchId);

        void getMatchStat(String matchId);

        void getMatchPlayerInfos(String matchId);
    }


    /**
     *  比赛详情view
     */
    public interface IMatchDetailView extends IBaseView {
        //展示比赛信息头部
        void showMatchInfoHeader(MatchDetailHeaderInfoBean matchDetailHeaderInfoBean);
        //展示比赛总结
        void showMatchSummary(MatchSummary matchSummary);
    }

    /**
     * 比赛统计view
     */
    public interface IMatchStatView extends IBaseView {

         void getGoalPlayersInfo(GoalPlayers goalPlayers);

         void getMatchTimelineInfo(MatchStatBean matchStatBean);
    }

    public interface IMatchPlayerInfoView extends IBaseView {
        void getMatchPlayerInfo();
    }
}
