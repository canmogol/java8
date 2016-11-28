package com.fererlab.conditional;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cond {

	private static Logger logger = Logger.getLogger(Cond.class.getName());
	private ExceptionUnit exceptionUnit = (e) -> {
		logger.log(Level.SEVERE, "got exception while cond, error: " + e, e);
	};
	private boolean state = false;

	private Cond() {
	}

	public static Cond create() {
		return new Cond();
	}

	public Cond check(Check check, Unit unit) {
		try {
			this.state = check.apply();
			if (this.state) {
				unit.apply();
			}
		} catch (Exception e) {
			handleError(e);
		}
		return this;
	}

	public Cond otherwise(Unit unit) {
		if (!this.state) {
			try {
				unit.apply();
			} catch (Exception e) {
				handleError(e);
			}
		}
		return this;
	}

	public Cond error(ExceptionUnit exceptionUnit) {
		this.exceptionUnit = exceptionUnit;
		return this;
	}

	private void handleError(Exception e) {
		exceptionUnit.apply(e);
	}

}
