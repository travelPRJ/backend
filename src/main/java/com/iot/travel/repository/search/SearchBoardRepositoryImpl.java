package com.iot.travel.repository.search;

import com.iot.travel.entity.QReply;
import com.iot.travel.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.iot.travel.entity.Board;
import com.iot.travel.entity.QBoard;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1.............");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QUser user = QUser.user;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(user).on(board.buno.eq(user));
        jpqlQuery.leftJoin(reply).on(reply.rbno.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, user.uno, reply.count());

        tuple.groupBy(board);

        log.info("-----------------------------------");
        log.info(tuple);
        log.info("-----------------------------------");

        List<Tuple> result = tuple.fetch();
        log.info(result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, Pageable pageable) {
        log.info("searchPage.........................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QUser user = QUser.user;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(user).on(board.buno.eq(user));
        jpqlQuery.leftJoin(reply).on(reply.rbno.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, user, reply.count());


        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        // 글이 삭제된 경우 포함시키지 않음
        booleanBuilder.and(expression)
                .and(board.bdelete.ne(1));

        // 댓글이 삭제된 경우 포함시키지 않음
        booleanBuilder.and(
                reply.rdelete.ne(1).or(reply.isNull())
        );

        booleanBuilder.and(expression);
        if (type != null) {
            String[] answer = type.split(":");          // ":" 중심으로 나눔 Ex. t:20  -> [0]=t / [1]=20
            String[] typeArr = answer[0].split("");     // 만약 다중 검색 조건 tn or tc라고 있을 경우 t , n 이런식으로 나눔
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            if (answer.length > 1 && !StringUtils.isEmpty(answer[1])) {
                for (String t : typeArr) {
                    switch (t) {
                        case "t":                           // btitle
                            conditionBuilder.or(board.btitle.contains(answer[1]));
                            break;
                        case "n":                           // nickname
                            conditionBuilder.or(user.nickname.contains(answer[1]));
                            break;
                        case "c":                           // bcontent
                            conditionBuilder.or(board.bcontent.contains(answer[1]));
                            break;
                    }
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);
        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        tuple.groupBy(board);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();
        log.info("COUNT: " + count);

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count
        );

    }

}
