import synthesizer.GuitarString;
public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] stringArray = new GuitarString[37];

    public static void stringArrayInitialize() {
        for (double i = 0; i < 37; i++) {
            stringArray[(int) i] = new GuitarString(440 * Math.pow(2, ((i - 24) / 12)));
        }
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        stringArrayInitialize();
        GuitarString gs = null;
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int idx = KEYBOARD.indexOf(key);
                if (idx > -1 && idx < 37) {
                    gs = stringArray[idx];
                    gs.pluck();
                } else {
                    continue;
                }
            }

            /* compute the superposition of samples */
//            double sample = stringA.sample() + stringC.sample();

            /* play the sample on standard audio */
            StdAudio.play(gs.sample());

            /* advance the simulation of each guitar string by one step */
//            .tic();
        }
    }
}
