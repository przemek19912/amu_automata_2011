package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Testy różnych operacji na automatach.
 */
public class TestAutomataOperations extends TestCase {

    /**
     * Test prostego automatu.
     */
    public final void testSimpleAutomaton() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q1);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
        State q10 = automatonB.addState();
        State q11 = automatonB.addState();
        State q12 = automatonB.addState();
        automatonB.addTransition(q10, q11, new CharTransitionLabel('a'));
        automatonB.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('a'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q10);
        automatonB.markAsFinal(q12);


        // proszę odkomentować, kiedy AutomataOperations.intersection
        // będzie gotowe!!!
        // AutomatonSpecification Result = AutomataOperations.intersection(automatonA, automatonB);
        // AutomatonByRecursion automaton = AutomatonByRecursion(Result);

        // assertTrue(automaton.accepts("aa"));
        // assertTrue(automaton.accepts("ab"));
        // assertFalse(automaton.accepts(""));
        // assertFalse(automaton.accepts("a"));

    }

    /**
     * Test sprawdza metode Sum w AutomataOperations A.
     */
    public final void testSumA() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q1);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification result = AutomataOperations.sum(automatonA, automatonB);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("ba"));
        assertTrue(automaton.accepts("aaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaa"));
        assertTrue(automaton.accepts("bb"));
        assertTrue(automaton.accepts("abbbbabbbabbb"));
        assertFalse(automaton.accepts("bbb"));
        assertFalse(automaton.accepts("tegomaniezakceptowac"));
        assertFalse(automaton.accepts("baaaaaaaaaa"));
        assertFalse(automaton.accepts("aaaaaaaaaaaaaaaxaaaaaa"));
        assertFalse(automaton.accepts("bab"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations B.
     */
    public final void testSumB() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonD = new NaiveAutomatonSpecification();

        State q0D = automatonD.addState();
        State q1D = automatonD.addState();
        State q2D = automatonD.addState();
        State q3D = automatonD.addState();
        automatonD.addTransition(q0D, q1D, new CharTransitionLabel('a'));
        automatonD.addTransition(q0D, q2D, new CharTransitionLabel('b'));
        automatonD.addTransition(q1D, q3D, new CharTransitionLabel('a'));
        automatonD.addTransition(q1D, q2D, new CharTransitionLabel('b'));
        automatonD.addTransition(q2D, q0D, new CharTransitionLabel('c'));
        automatonD.addTransition(q2D, q1D, new CharTransitionLabel('b'));
        automatonD.addTransition(q2D, q3D, new CharTransitionLabel('a'));
        automatonD.addTransition(q3D, q2D, new CharTransitionLabel('c'));
        automatonD.addTransition(q3D, q0D, new CharTransitionLabel('b'));
        automatonD.markAsInitial(q0D);
        automatonD.markAsFinal(q3D);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonD);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("ab"));
        assertTrue(automaton.accepts("abbabba"));
        assertTrue(automaton.accepts("bbbcaacba"));
        assertTrue(automaton.accepts("aacacaca"));
        assertTrue(automaton.accepts("aa"));
        assertFalse(automaton.accepts("zle"));
        assertFalse(automaton.accepts("b"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("aac"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations C.
     */
    public final void testSumC() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();

        State q0C = automatonC.addState();
        automatonC.addLoop(q0C, new CharTransitionLabel('a'));
        automatonC.addLoop(q0C, new CharTransitionLabel('b'));
        automatonC.addLoop(q0C, new CharTransitionLabel('c'));
        automatonC.addLoop(q0C, new CharTransitionLabel('d'));
        automatonC.markAsInitial(q0C);
        automatonC.markAsFinal(q0C);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonC);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("babbaccddcaaccb"));
        assertTrue(automaton.accepts("bbaccddbaba"));
        assertTrue(automaton.accepts("bbbcaacba"));
        assertTrue(automaton.accepts("aaaaaaaaaaaaaaaa"));
        assertTrue(automaton.accepts(""));
        assertFalse(automaton.accepts("bbaccddxbaba"));
        assertFalse(automaton.accepts("czytwojprogrammackutozaakceptuje"));
        assertFalse(automaton.accepts("zielonosmutnaniebieskowesolapomaranczowa"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations D.
     */
    public final void testSumD() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonE = new NaiveAutomatonSpecification();

        State q0E = automatonE.addState();
        automatonE.addTransition(q0E, q0E, new EpsilonTransitionLabel());
        automatonE.markAsInitial(q0E);
        automatonE.markAsFinal(q0E);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonE);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts(""));
        assertTrue(automaton.accepts("aa"));
        assertFalse(automaton.accepts("bbaccddxbaba"));
        assertFalse(automaton.accepts("aabbbaaaa"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations E.
     */
    public final void testSumE() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonF = new NaiveAutomatonSpecification();

        State q0F = automatonF.addState();
        State q1F = automatonF.addState();
        State q2F = automatonF.addState();
        State q3F = automatonF.addState();
        State q7F = automatonF.addState();
        State q5F = automatonF.addState();
        State q6F = automatonF.addState();
        automatonF.addTransition(q0F, q1F, new CharTransitionLabel('a'));
        automatonF.addTransition(q0F, q3F, new EpsilonTransitionLabel());
        automatonF.addTransition(q0F, q2F, new EpsilonTransitionLabel());
        automatonF.addTransition(q3F, q7F, new CharTransitionLabel('a'));
        automatonF.addTransition(q2F, q5F, new EpsilonTransitionLabel());
        automatonF.addTransition(q5F, q6F, new CharTransitionLabel('b'));
        automatonF.markAsInitial(q0F);
        automatonF.markAsFinal(q1F);
        automatonF.markAsFinal(q7F);
        automatonF.markAsFinal(q6F);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonF);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("b"));
        assertTrue(automaton.accepts("a"));
        assertFalse(automaton.accepts("aaabbbb"));
        assertFalse(automaton.accepts(""));
    }
    
    /**
     * Test metody zwracającej automat akceptujący różnice dwoch automatow
     */
    public void testdifference() {
        
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q0);
        
        automaton.addLoop(q0, new CharTransitionLabel('a'));
        automaton.addLoop(q0, new CharTransitionLabel('b'));
        
        AutomatonSpecification automaton2 = new NaiveAutomatonSpecification();

        State q0a = automaton2.addState();
        
        automaton2.markAsInitial(q0a);
        automaton2.markAsFinal(q0a);
        
        automaton2.addLoop(q0a, new CharTransitionLabel('a'));
        
        AutomatonSpecification result = AutomataOperations.diff(automaton, automaton2);
        
        
        NondeterministicAutomatonByThompsonApproach automaton3 = new
        NondeterministicAutomatonByThompsonApproach(result);
        
        assertTrue(automaton3.accepts("bbbbbbbbbbbbbbbbbbbbbbb"));
        assertFalse(automaton3.accepts("aaabbbaabb"));
        assertFalse(automaton3.accepts("aaa"));
        
      
        
    }
    public void testdifference2() {
        
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);
        
        automaton.addTransition(q0,q1, new CharTransitionLabel('b'));
        automaton.addLoop(q0, new CharTransitionLabel('a'));
        automaton.addLoop(q1, new CharTransitionLabel('a'));
        automaton.addLoop(q1, new CharTransitionLabel('b'));
        
        
        AutomatonSpecification automaton2 = new NaiveAutomatonSpecification();

        State q0a = automaton2.addState();
        
        automaton2.markAsInitial(q0a);
        automaton2.markAsFinal(q0a);
        
        automaton2.addLoop(q0a, new CharTransitionLabel('a'));
        
        AutomatonSpecification result = AutomataOperations.diff(automaton, automaton2);
        
        
        NondeterministicAutomatonByThompsonApproach automaton3 = new
        NondeterministicAutomatonByThompsonApproach(result);
        
        assertTrue(automaton3.accepts("b"));
        assertTrue(automaton3.accepts("baab"));
        assertTrue(automaton3.accepts("baa"));
        assertTrue(automaton3.accepts("bbbbbbbbbbb"));
        assertTrue(automaton3.accepts("bbbbbbbbbbbbaaaaaaaaaaaaaaaa"));
        assertFalse(automaton3.accepts("aaabbbaabb"));
        assertFalse(automaton3.accepts("aaa"));
        assertFalse(automaton3.accepts("a"));
        
      
    }
    public void testdifference3() {
        
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);
        
        automaton.addTransition(q0,q1, new CharTransitionLabel('a'));
        automaton.addTransition(q0,q1, new CharTransitionLabel('b'));
              
        
        AutomatonSpecification automaton2 = new NaiveAutomatonSpecification();

        State q0a = automaton2.addState();
        State q1a = automaton2.addState();
        State q2a = automaton2.addState();
        
        automaton2.markAsInitial(q0a);
        automaton2.markAsFinal(q2a);
        
        automaton2.addTransition(q0a,q1a, new CharTransitionLabel('c'));
        automaton2.addTransition(q0a,q1a, new CharTransitionLabel('d'));
        automaton2.addTransition(q1a,q2a, new CharTransitionLabel('d'));
        
        AutomatonSpecification result = AutomataOperations.diff(automaton, automaton2);
        
        
        NondeterministicAutomatonByThompsonApproach automaton3 = new
        NondeterministicAutomatonByThompsonApproach(result);
        
        assertTrue(automaton3.accepts("a"));
        assertTrue(automaton3.accepts("b"));
        assertFalse(automaton3.accepts("aaabbbaabb"));
        assertFalse(automaton3.accepts("d"));
        assertFalse(automaton3.accepts("c"));
    } 
    
    public void testdifference4() {
        
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);
        
        automaton.addTransition(q0,q1, new CharTransitionLabel('a'));
        automaton.addTransition(q0,q1, new CharTransitionLabel('b'));
              
        
        AutomatonSpecification automaton2 = new NaiveAutomatonSpecification();

        State q0a = automaton.addState();
        State q1a = automaton.addState();
        
        automaton.markAsInitial(q0a);
        automaton.markAsFinal(q1a);
        
        automaton.addTransition(q0a,q1a, new CharTransitionLabel('a'));
        automaton.addTransition(q0a,q1a, new CharTransitionLabel('b'));
              
        AutomatonSpecification result = AutomataOperations.diff(automaton, automaton2);
        
        
        
        assertTrue(result.isEmpty());
        
    }
}
