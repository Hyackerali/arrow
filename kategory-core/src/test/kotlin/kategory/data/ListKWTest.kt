package kategory

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.shouldNot
import io.kotlintest.matchers.shouldNotBe
import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class)
class ListKWTest : UnitSpec() {
    val applicative = ListKW.applicative()

    init {

        "instances can be resolved implicitly" {
            functor<ListKWHK>() shouldNotBe null
            applicative<ListKWHK>() shouldNotBe null
            monad<ListKWHK>() shouldNotBe null
            foldable<ListKWHK>() shouldNotBe null
            traverse<ListKWHK>() shouldNotBe null
            semigroupK<ListKWHK>() shouldNotBe null
            semigroup<ListKW<Int>>() shouldNotBe null
            monoid<ListKW<Int>>() shouldNotBe null
            monoidK<ListKW<ListKWHK>>() shouldNotBe null
            monadCombine<ListKW<ListKWHK>>() shouldNotBe null
            functorFilter<ListKW<ListKWHK>>() shouldNotBe null
            monadFilter<ListKW<ListKWHK>>() shouldNotBe null
        }

        testLaws(MonadLaws.laws(ListKW.monad(), Eq.any()))
        testLaws(SemigroupKLaws.laws(ListKW.semigroupK(), applicative, Eq.any()))
        testLaws(MonoidKLaws.laws(ListKW.monoidK(), applicative, Eq.any()))
        testLaws(TraverseLaws.laws(ListKW.traverse(), applicative, { n: Int -> ListKW(listOf(n)) }, Eq.any()))
    }
}