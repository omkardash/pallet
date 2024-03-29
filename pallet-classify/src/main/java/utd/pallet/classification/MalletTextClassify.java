package utd.pallet.classification;

import java.io.Serializable;
import java.util.ArrayList;

import utd.pallet.data.MalletAccuracyVector;
import cc.mallet.classify.Classification;
import cc.mallet.classify.Classifier;
import cc.mallet.classify.Trial;
import cc.mallet.pipe.Noop;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.iterator.ArrayIterator;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;

/**
 * classifies text document using the specified classifier.
 * 
 * 
 * 
 */
@SuppressWarnings( { "serial" })
public class MalletTextClassify implements Serializable {

    /**
     * Place holder for classification instance.
     */
    private Trial trial = null;

    /**
     * Default constructor
     * 
     */
    public MalletTextClassify() {
        trial = null;
    }

    /**
     * 
     * @param classifier
     *            Instance of Classifier, used for classifying the test data.
     * @param listToClassify
     *            Test data List that needs to be classified, Valid types of
     *            objects that can be passed : InstanceList, Instance, String,
     *            String [].
     * @return List of Instances of type Classification that contains the
     *         classification info.
     * 
     * @throws Exception
     *             If Classification algorithm is unknown.
     * @throws NullPointerException
     *             If either Classifier or Object parameter is null.
     */
    public ArrayList<Classification> classify(Classifier classifier,
            Object listToClassify) throws Exception, NullPointerException {
        Pipe pipe = null;
        Instance instance = null;
        InstanceList iList = null;

        if (classifier == null) {
            throw new NullPointerException("Invalid Classifier object");
        }

        pipe = classifier.getInstancePipe();
        if (listToClassify == null) {
            throw new NullPointerException("");
        }

        if (listToClassify instanceof InstanceList) {

            trial = new Trial(classifier, (InstanceList) listToClassify);

        } else if (listToClassify instanceof String) {

            iList = new InstanceList(new Noop());
            instance = pipe.instanceFrom(new Instance(listToClassify, pipe
                    .getTargetAlphabet().lookupObject(0), null, null));
            iList.add(instance);
            trial = new Trial(classifier, iList);

        } else if (listToClassify instanceof Instance) {
            Instance testInstance = (Instance) listToClassify;
            iList = new InstanceList(new Noop());
            iList.add(testInstance);
            trial = new Trial(classifier, iList);

        } else if (listToClassify instanceof String[]) {

            iList = new InstanceList(pipe);
            iList.addThruPipe(new ArrayIterator((String[]) listToClassify, pipe
                    .getTargetAlphabet().lookupObject(0)));

            trial = new Trial(classifier, iList);
        } else {

            throw new Exception("Unsupported data type for classification");
        }

        ArrayList<Classification> aList = new ArrayList<Classification>();

        for (int i = 0; i < trial.size(); i++) {
            aList.add(trial.get(i));
        }

        // Classification cl = trial.get(0);
        return aList;
    }

    /**
     * 
     * @return ArrayList of MalletAccuracyVector.
     * @throws NullPointerException
     *             if called before classifying the data.
     * @see MalletAccuracyVector
     */
    public ArrayList<MalletAccuracyVector> getAccuracyVectors()
            throws NullPointerException {

        ArrayList<MalletAccuracyVector> accVectorList = new ArrayList<MalletAccuracyVector>();
        //

        for (int i = 0; i < trial.size(); i++) {

            Classification cl = this.trial.get(i);
            MalletAccuracyVector accuracyVector = new MalletAccuracyVector();
            try {
                accuracyVector.setAccuracy(cl);
            } catch (Exception e) {
            	e.printStackTrace();
            }

            accVectorList.add(accuracyVector);
        }

        return accVectorList;
    }
}
