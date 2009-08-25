package utd.pallet.classification;

import java.io.IOException;
import java.util.regex.Pattern;

import cc.mallet.classify.Classification;
import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.FeatureSequence2FeatureVector;
import cc.mallet.pipe.Input2CharSequence;
import cc.mallet.pipe.Target2Label;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.TokenSequenceRemoveStopwords;
import cc.mallet.pipe.iterator.ArrayIterator;

/**
 * @author sharath
 * 
 */
public class TestMalletClassifier {

    /**
     * @param args
     *            Command line arguments .
     */
    public static void main(String[] args) {

        /**
         * The data is provided with the labels(on the basis of which
         * classification will be done)
         * 
         */

        String[][][] trainingdata = new String[][][] {
                {{ "on the plains of africa the lions roar",
                   "in swahili ngoma means to dance",
                   "nelson mandela became president of south africa",
                   "the saraha dessert is expanding" }, { "africa" }},
                {{ "panda bears eat bamboo",
                   "china's one child policy has resulted in a surplus of boys",
                   "tigers live in the jungle" }, { "asia" } },
                {{ "home of kangaroos", "Autralian's for beer - Foster",
                   "Steve Irvin is a herpetologist" },{ "australia" } } };

        MalletClassifier mc = new MalletClassifier();

        /**
         * The Pattern matches all the alphabets and numerics
         * 
         */

        Pattern tokenPattern = Pattern.compile("[\\p{L}\\p{N}_]+");

        /**
         * Pipes will be added to the pipelist. The data will be converted into
         * Mallet instances passing through these pipes
         * 
         */

        try {
            mc.CreatePipe(new Input2CharSequence("UTF-8"),
                    new CharSequence2TokenSequence(tokenPattern), true,
                    new TokenSequenceRemoveStopwords(true, true),
                    new TokenSequence2FeatureSequence(), new Target2Label(),
                    new FeatureSequence2FeatureVector(false), true);
        } catch (IOException e) {

        }

        /**
         * Adds all the instances generated by the iterator to the Instance
         * List.
         * 
         */
        for (int i = 0; i < 3; i++) {
            try {
                mc.addThruPipe(new ArrayIterator(trainingdata[i][0],
                        trainingdata[i][1][0]));

            } catch (Exception e) {
            }
        }

        /**
         * The Classifier will be trained with the sample data using Naivebayes
         * algorithm
         * 
         */

        try {
            mc.train(MalletClassifier.naivebayes);
            // mc.train(3);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }

        /**
         * The test data will be provided to the trained Classifier
         * 
         */

        Classification c = mc.classify("nelson mandela never eats lions");
        System.out.println("Class Name - " + c.getLabeling().getBestLabel());

        /**
         * The classifier is trained again with the help of maxent algorithm
         * 
         */

        try {
            mc.train(MalletClassifier.maxent);
        } catch (Exception e) {
        }

        /**
         * The test data will be provided to the trained Classifier
         * 
         */

        c = mc.classify("nelson mandela never eats lions");
        System.out.println("Class Name - " + c.getLabeling().getBestLabel());

        c = mc
                .classify("Steve Irvin studies kangaroos also, He is not just a herpetologist");
        System.out.println("Class Name - " + c.getLabeling().getBestLabel());

        try {
            mc.train(MalletClassifier.naivebayes);
        } catch (Exception e) {
        }

        c = mc.classify("nelson mandela never eats lions");
        System.out.println("Class Name - " + c.getLabeling().getBestLabel());

        c = mc
                .classify("Steve Irvin studies kangaroos also, He is not just a herpetologist");
        System.out.println("Class Name - " + c.getLabeling().getBestLabel());
    }
}
