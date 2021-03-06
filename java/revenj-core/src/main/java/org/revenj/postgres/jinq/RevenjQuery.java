package org.revenj.postgres.jinq;

import org.revenj.patterns.Specification;
import org.revenj.patterns.DataSource;
import org.revenj.patterns.Query;
import org.revenj.postgres.jinq.transform.LambdaInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

final class RevenjQuery<T extends DataSource> implements Query<T> {
	private final RevenjQueryComposer<T> queryComposer;

	public RevenjQuery(RevenjQueryComposer<T> query) {
		queryComposer = query;
	}

	static <U extends DataSource> RevenjQuery<U> makeQueryStream(RevenjQueryComposer<U> query) {
		return new RevenjQuery<>(query);
	}

	@FunctionalInterface
	interface CustomAnalysis {
		LambdaInfo getAnalysisLambda(int index);
	}

	private RevenjQueryComposer applyWhere(Specification predicate) {
		if (predicate == null) {
			return queryComposer;
		}
		return predicate instanceof CustomAnalysis
				? queryComposer.where(((CustomAnalysis) predicate).getAnalysisLambda(queryComposer.getLambdaCount()))
				: queryComposer.where(LambdaInfo.analyze(predicate, queryComposer.getLambdaCount(), true));
	}

	private RevenjQueryComposer applyOrder(Compare order, boolean ascending) {
		return order instanceof CustomAnalysis
				? queryComposer.sortedBy(((CustomAnalysis) order).getAnalysisLambda(queryComposer.getLambdaCount()), ascending)
				: queryComposer.sortedBy(LambdaInfo.analyze(order, queryComposer.getLambdaCount(), true), ascending);
	}

	@Override
	public Query<T> filter(Specification<T> predicate) {
		if (predicate == null) return this;
		return makeQueryStream(applyWhere(predicate));
	}

	@Override
	public Query<T> skip(long n) {
		RevenjQueryComposer newComposer = this.queryComposer.skip(n);
		return makeQueryStream(newComposer);
	}

	@Override
	public Query<T> limit(long n) {
		RevenjQueryComposer newComposer = this.queryComposer.limit(n);
		return makeQueryStream(newComposer);
	}

	@Override
	public <V> Query<T> sortedBy(Compare<T, V> order) {
		return makeQueryStream(applyOrder(order, true));
	}

	@Override
	public <V> Query<T> sortedDescendingBy(Compare<T, V> order) {
		return makeQueryStream(applyOrder(order, false));
	}

	@Override
	public long count() throws IOException {
		try {
			return queryComposer.count();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public boolean anyMatch(Specification<? super T> predicate) throws IOException {
		try {
			return applyWhere(predicate).any();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public boolean allMatch(Specification<? super T> predicate) throws IOException {
		try {
			LambdaInfo lambda = predicate instanceof CustomAnalysis
					? ((CustomAnalysis) predicate).getAnalysisLambda(queryComposer.getLambdaCount())
					: LambdaInfo.analyze(predicate, queryComposer.getLambdaCount(), true);
			return queryComposer.all(lambda);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public boolean noneMatch(Specification<? super T> predicate) throws IOException {
		try {
			return applyWhere(predicate).none();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Optional<T> findFirst() throws IOException {
		try {
			return queryComposer.first();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Optional<T> findAny() throws IOException {
		try {
			return queryComposer.first();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<T> list() throws IOException {
		try {
			return queryComposer.toList();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
