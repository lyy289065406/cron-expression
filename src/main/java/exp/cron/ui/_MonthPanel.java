package exp.cron.ui;

import java.util.List;

import javax.swing.JCheckBox;

import exp.libs.utils.num.NumUtils;
import exp.libs.utils.other.StrUtils;
import exp.libs.utils.verify.RegexUtils;
import exp.libs.warp.task.cron.Cron;
import exp.libs.warp.task.cron._Month;
import exp.libs.warp.ui.cpt.cbg.CheckBoxGroup;

/**
 * <PRE>
 * cron表达式-月域界面
 * </PRE>
 * <br/><B>PROJECT : </B> cron-expression
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   2017-10-30
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class _MonthPanel extends __TimePanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -7775377611552787648L;
	
	/**
	 * 构造函数
	 * @param cron cron表达式对象
	 * @param name 子界面名称
	 */
	protected _MonthPanel(Cron cron, String name) {
		super(cron, name, 3);
	}
	
	@Override
	protected void initTips() {
		setRangeTooltips(tfFrom, _Month.MIN, _Month.MAX);
		setRangeTooltips(tfTo, _Month.MIN, _Month.MAX);
		setRangeTooltips(tfBegin, _Month.MIN, _Month.MAX);
		setRangeTooltips(tfStep, STEP, -1);
	}

	@Override
	protected CheckBoxGroup<String> initSequence() {
		String[] months = new String[] {
			StrUtils.concat(_Month.JAN, " (", 1, ")"), 
			StrUtils.concat(_Month.FEB, " (", 2, ")"), 
			StrUtils.concat(_Month.MAR, " (", 3, ")"), 
			StrUtils.concat(_Month.APR, " (", 4, ")"), 
			StrUtils.concat(_Month.MAY, " (", 5, ")"), 
			StrUtils.concat(_Month.JUN, " (", 6, ")"), 
			StrUtils.concat(_Month.JUL, " (", 7, ")"), 
			StrUtils.concat(_Month.AUG, " (", 8, ")"), 
			StrUtils.concat(_Month.SEP, " (", 9, ")"), 
			StrUtils.concat(_Month.OCT, " (", 10, ")"), 
			StrUtils.concat(_Month.NOV, " (", 11, ")"), 
			StrUtils.concat(_Month.DEC, " (", 12, ")")
		};
		return new CheckBoxGroup<String>(months);
	}

	@Override
	protected void setEveryBtnListener() {
		cron.Month().withEvery();
	}

	@Override
	protected void setRangeBtnListener() {
		if(StrUtils.isEmpty(tfFrom.getText())) {
			tfFrom.setText(String.valueOf(_Month.MIN));
		}
		
		if(StrUtils.isEmpty(tfTo.getText())) {
			tfTo.setText(String.valueOf(_Month.MAX));
		}
		
		int from = NumUtils.toInt(tfFrom.getText(), _Month.MIN);
		int to = NumUtils.toInt(tfTo.getText(), _Month.MAX);
		cron.Month().withRange(from, to);
	}

	@Override
	protected void setStepBtnListener() {
		if(StrUtils.isEmpty(tfBegin.getText())) {
			tfBegin.setText(String.valueOf(_Month.MIN));
		}
		
		if(StrUtils.isEmpty(tfStep.getText())) {
			tfStep.setText(String.valueOf(STEP));
		}
		
		int begin = NumUtils.toInt(tfBegin.getText(), _Month.MIN);
		int interval = NumUtils.toInt(tfStep.getText(), STEP);
		cron.Month().withStep(begin, interval);
	}
	
	@Override
	protected void setSeqBtnListener(List<JCheckBox> selecteds) {
		int[] seqs = new int[selecteds.size()];
		for(int i = 0; i < seqs.length; i++) {
			JCheckBox selected = selecteds.get(i);
			int val = NumUtils.toInt(
					RegexUtils.findFirst(selected.getText(), "(\\d)"), -1);
			if(val >= 0) {
				seqs[i] = val;
			}
		}
		cron.Month().withSequence(seqs);
	}
	
}
