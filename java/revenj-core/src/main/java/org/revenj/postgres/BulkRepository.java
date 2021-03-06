package org.revenj.postgres;

import org.revenj.patterns.Specification;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public interface BulkRepository<T> {
	BiFunction<ResultSet, Integer, Optional<T>> find(BulkReaderQuery query, String uri);

	BiFunction<ResultSet, Integer, List<T>> find(BulkReaderQuery query, String[] uri);

	BiFunction<ResultSet, Integer, List<T>> search(BulkReaderQuery query, Specification<T> filter, Integer limit, Integer offset);

	BiFunction<ResultSet, Integer, Long> count(BulkReaderQuery query, Specification<T> filter);

	BiFunction<ResultSet, Integer, Boolean> exists(BulkReaderQuery query, Specification<T> filter);
}
