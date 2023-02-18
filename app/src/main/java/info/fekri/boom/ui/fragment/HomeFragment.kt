package info.fekri.boom.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.fekri.boom.databinding.FragmentHomeBinding
import info.fekri.boom.ui.activity.ShowDataActivity
import info.fekri.boom.ux.adapter.HistoryAdapter
import info.fekri.boom.ux.adapter.ItemEvents
import info.fekri.boom.ux.adapter.PomesAdapter
import info.fekri.boom.ux.data.Book
import info.fekri.boom.ux.adapter.ScienceAdapter

const val KEY_SEND_DATA_SHOW = "send_data_to_show"

class HomeFragment : Fragment(), ItemEvents {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        scienceUi()
        historyUi()
        poemsUi()
    }

    private fun poemsUi() {
        val data = arrayListOf<Book>(
            Book(
                "The Parakeets",
                "They talk all day\n" +
                        "and when it starts to get dark\n" +
                        "they lower their voices\n" +
                        "to converse with their own shadows\n" +
                        "and with the silence.\n" +
                        "\n" +
                        "They are like everybody\n" +
                        "—the parakeets—\n" +
                        "all day chatter,\n" +
                        "and at night bad dreams.\n" +
                        "\n" +
                        "With their gold rings\n" +
                        "on their clever faces,\n" +
                        "brilliant feathers\n" +
                        "and the heart restless\n" +
                        "with speech...\n" +
                        "\n" +
                        "They are like everybody,\n" +
                        "—the parakeets—\n" +
                        "the ones that talk best\n" +
                        "have separate cages.",
                "https://artprojectsforkids.org/wp-content/uploads/2014/12/Pop-Art-Landscape-Post.jpg",
                "https://learnwithhomer.com/library/genre/science/"
            ),
            Book(
                "The Tyger",
                "Tyger! Tyger! burning bright\n" +
                        "In the forests of the night,\n" +
                        "What immortal hand or eye\n" +
                        "Could frame thy fearful symmetry?\n" +
                        "\n" +
                        "In what distant deeps or skies\n" +
                        "Burnt the fire of thine eyes?\n" +
                        "On what wings dare he aspire?\n" +
                        "What the hand, dare sieze the fire?\n" +
                        "\n" +
                        "And what shoulder, & what art,\n" +
                        "Could twist the sinews of thy heart?\n" +
                        "And when thy heart began to beat,\n" +
                        "What dread hand? & what dread feet?\n" +
                        "\n" +
                        "What the hammer? what the chain?\n" +
                        "In what furnace was thy brain?\n" +
                        "What the anvil? what dread grasp\n" +
                        "Dare its deadly terrors clasp?\n" +
                        "\n" +
                        "When the stars threw down their spears,\n" +
                        "And water'd heaven with their tears,\n" +
                        "Did he smile his work to see?\n" +
                        "Did he who made the Lamb make thee?\n" +
                        "\n" +
                        "Tyger! Tyger! burning bright\n" +
                        "In the forests of the night,\n" +
                        "What immortal hand or eye\n" +
                        "Dare frame thy fearful symmetry?",
                "https://artprojectsforkids.org/wp-content/uploads/2014/12/Pop-Art-Landscape-Post.jpg",
                "https://learnwithhomer.com/library/genre/science/"
            ),
            Book(
                "My Father. A Tree.",
                "Today, longing for my father, \n" +
                        "I saw a solitary bleached owl skim \n" +
                        "the dark grasses. It swept so low \n" +
                        "to the ground it might have buried itself. \n" +
                        "I did not know my father so how could I \n" +
                        "be lonely for that guardian?\n" +
                        "\n" +
                        "When I was a newborn, I didn’t let\n" +
                        "my father hold me. I cried in his presence\n" +
                        "till my mother came. My father would shrug, \n" +
                        "lean into his high backed chair, to read the paper,\n" +
                        "to smoke his pipe while he heard his wife\n" +
                        "sing to his only daughter. \n" +
                        "\n" +
                        "In the woods, I summon him\n" +
                        "and my eyes fool me as a dark haired\n" +
                        "jay shifts a twig, or a stone rolls \n" +
                        "into the creek. I think I hear his footsteps\n" +
                        "on the path, but it is only the oak\n" +
                        "hip twitching to the afternoon’s cold wind. \n" +
                        "\n" +
                        "When I was born, he must have felt\n" +
                        "the rupture in his chest, dark matter funneling\n" +
                        "through his veins, and he must have known \n" +
                        "he would not be here for the rest but he ushered \n" +
                        "me into that brightly lit room, the earth\n" +
                        "with all its lumen.\n" +
                        "\n" +
                        "Father, I know you are here, \n" +
                        "the only place you must be, \n" +
                        "where the heavy branches \n" +
                        "lean into bright air.\n" +
                        "\n" +
                        "I put down my sack to eat everything\n" +
                        "I have carried with me. When I am done, \n" +
                        "the ants come swarming in to take \n" +
                        "the last of it, to cleanse the earth \n" +
                        "of abundance and discard.\n" +
                        "\n" +
                        "Walking in these woods, I believe\n" +
                        "that tall shadows and shifts of light \n" +
                        "mean that something is at work beyond me. \n" +
                        "\n" +
                        "Midway home and the redwood\n" +
                        "are letting go their furious scent,\n" +
                        "where you are the tree left standing\n" +
                        "and I am this frozen salt flat, \n" +
                        "hemisphere of crushed snow. ",
                "https://artprojectsforkids.org/wp-content/uploads/2014/12/Pop-Art-Landscape-Post.jpg",
                "https://learnwithhomer.com/library/genre/science/"
            )
        )
        val poemsAdapter = PomesAdapter(data, this)
        binding.recyclerPhilosophy.adapter= poemsAdapter
        binding.recyclerPhilosophy.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun historyUi() {
        val data = arrayListOf<Book>(
            Book(
                "History Stormy-time!",
                "2000 years ago Rome was in chaos. The great Roman general Julius Caesar had been murdered. He was stabbed to death by nobles, including his friend, Brutus. They had believed he wanted to make himself King. They did not want to be ruled by a King.\n" +
                        "\n" +
                        "\n" +
                        "The murderers of Caesar were delighted with themselves. They thought they had saved Rome. However, Julius Caesar’s friends were furious. One of his friends was called Mark Antony. He was asked to speak at Julius Caesar’s funeral. Thousands of ordinary Romans were there. Mark Antony gave a speech in which he reminded everyone how much Caesar had loved the ordinary people. He also told them how in Caesar’s will he had left money for the poor people of Rome. Then he showed the crowd Caesar’s torn and bloody toga from when he had been killed. The crowd were furious. They turned on the murderers of Caesar and hunted them through the streets of Rome. Brutus and the other murderers fled.\n" +
                        "\n" +
                        "\n" +
                        "Caesar’s nephew was called Octavian. Caesar had adopted him as his son. He now called himself Octavian Caesar. Octavian and Mark Antony raised and army and chased Caesar’s murderers. They defeated them in battle. Brutus killed himself.\n" +
                        "\n" +
                        "\n" +
                        "Octavian and Mark Antony did not trust each other. They decided to divide up control of the Roman lands between them. Mark Antony took the East and ruled from Egypt. Octavian took the West and ruled from Rome. To make their alliance stronger, Mark Antony married Octavian’s sister, Octavia.\n" +
                        "\n" +
                        "\n" +
                        "However, in Egypt, Mark Antony met Cleopatra, the Queen of Egypt. She had been Julius Caesar’s girlfriend. Now she became Mark Antony’s girlfriend. People in Rome did not like this. Nor did Octavian. War broke out. There was a big naval battle but at the height of the battle, Cleopatra and her ships fled. Mark Antony followed her. The battle was lost. Then Mark Antony’s soldiers fled too.\n" +
                        "\n" +
                        "\n" +
                        "Mark Antony then believed that Cleopatra had killed herself. He was so upset he tried to kill himself with a sword. However, before he died he learned that Cleopatra was actually alive. He died in her arms. Cleopatra tried to make peace with Octavian but he was not interested. She killed herself by letting a snake bite her. She died from its poison.\n" +
                        "\n" +
                        "\n" +
                        "Octavian then took total control in Rome. He called himself Imperator which meant Commander. Over time this word changed and became our word for Emperor. The Roman Republic was over; now it was the Roman Empire.",
                "https://deow9bq0xqvbj.cloudfront.net/image-logo/3412877/History_Storytime_Aged_7.png",
                "https://www.historystorytime.com/"
            ),
            Book(
                "Dinosaur Poem",
                "I've never seen a real dinosaur.\n" +
                        "There are none around any more.\n" +
                        "They lived in prehistoric time,\n" +
                        "Let's learn about them in this rhyme.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Cute Dinosaurs\n" +
                        "\n" +
                        "Dinosaurs all had tiny brains\n" +
                        "But they all had ginormous names.\n" +
                        "Triceratops, brontosaurus,\n" +
                        "Velociraptor, stegosaurus.\n" +
                        "Pterodactyl (with a silent \"p\"),\n" +
                        "Ichthyothaur, that thwam in the thea,\n" +
                        "And there was the Iguanodon -\n" +
                        "That one had a spike for a thumb.\n" +
                        "\n" +
                        "The big tyrannosaurus rex\n" +
                        "Was very scary, one suspects.\n" +
                        "It had a lot of big sharp teeth,\n" +
                        "And a *ROAR* that defied belief.\n" +
                        "\n" +
                        "Most dinosaurs liked plants to eat\n" +
                        "Some preferred the taste of meat.\n" +
                        "But none of them ate chocolate\n" +
                        "It had not been invented yet.\n" +
                        "\n" +
                        "Dinosaurs did not eat candy\n" +
                        "And actually, this was quite handy;\n" +
                        "Candy causes teeth to rot,\n" +
                        "And dinosaurs had such a lot.\n" +
                        "\n" +
                        "I really wish that I could get\n" +
                        "A dinosaur to be my pet.\n" +
                        "I would take him to school one day\n" +
                        "And everyone would run away.\n" +
                        "\n" +
                        "This won't happen, I realize,\n" +
                        "Dinosaurs all met their demise.\n" +
                        "Fossils are all that's left, it seems,\n" +
                        "And you can see them in museums.",
                "http://www.history-for-kids.com/images/cute-dinosaurs.jpg",
                "http://www.history-for-kids.com/"
            ),
            Book(
                "Alexander the Great",
                "The best ever military commander\n" +
                        "Was a young king named Alexander.\n" +
                        "From the Macadonian city state\n" +
                        "He was known as Alexander the Great.\n" +
                        "A brave man - he had a lot of bottle;\n" +
                        "A wise man too - taught by Aristotle.\n" +
                        "He fought many battles & always won,\n" +
                        "His army never lost a single one.\n" +
                        "Turkey, Syria and Egypt all fell,\n" +
                        "So did Babylon, and Persia as well.\n" +
                        "But after 13 years of war, his men\n" +
                        "Said they wanted to go home again.\n" +
                        "That was the end of conquest & glory\n" +
                        "And that's the end of Alexander's story",
                "http://www.history-for-kids.com/images/alexander-the-great.jpg",
                "http://www.history-for-kids.com/"
            )
        )
        val historyAdapter = HistoryAdapter(data, this)
        binding.recyclerHistory.adapter = historyAdapter
        binding.recyclerHistory.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun scienceUi() {
        val data = arrayListOf<Book>(
            Book(
                "What's This?\n" +
                        "Breathing",
                "This is it—all systems go! Time for the final countdown. Five, four, three, two, one. Blastoff! We’re in orbit! Let’s get ready for a spacewalk. We’ll need our spacesuit and our boots and gloves and helmet . . . What’s outside? Look out the window. Out in space! Can you spacewalk your way to the broken satellite and repair it? Life on Mars? You’re the first astronaut on the big red planet. What will you find? Back to Earth! The shuttle lands on a runway just like a jet plane. See you soon for another adventure in the terrific spaceship!",
                "https://d3iq9oupxk0b1m.cloudfront.net/Manuscript_00776_HayleyRidesIntoSpace/1x/hayley_previewImage_inside_circle.3M2NQc.jpg",
                "https://www.amnh.org/explore/ology/stories"
            ),
            Book(
                "Optical Illusions!",
                "Hey, you're a scientist, like me! Come discover with Sid the Science Kid. “The pancakes are ready!“ “Pancakes!“ “There's one for Zeke and one for you.“ “Hey, why is this a smaller pancake? I mean, look how much bigger Zeke's pancake is than my pancake.“ “Nope, same size, Sid.“ “I'll use my trusty ruler to check 'em, Dad.“ “Okay Siddo, but I promise they're the same.“ “Okay, so the bigger pancake measures five inches. And over here, the small one measures . . . five inches! Incredible! They're the same, but how? Aw! Dad, how can their size be the same?“ “Well, I'll tell you, Sid. Your eyes were tricked by what's called an optical illusion. Your brain told you that the pancake on the left was bigger, but actually the two pancakes are the exact same size.“ “You mean, I can't believe my own eyes?!“ “Well, it's more like your brain played a trick on you.“ “My brain played a trick on me? Hold on. Something else must be different if the pancakes are the same size.“ “You're on the right track.“ “Well, the pancakes are the same size, but the plates are different. The plate on the left is smaller! That's why I thought the pancake on the left was bigger than the other one. Optical illusions are amazing!“ “Now take a look, Sid. I've got another one. What animal do you see in this drawing?“ “Um . . . a duck!“ “Wait. A duck? It looks like a little rabbit to me.“ “Huh? No, that's a duck.“ “If you see a duck, you imagine that this is his bill, these are his eyes . . . and this is the back of his head.“ “Yeah!“ “But watch this. On the other side, we can see a mouth, an eye, and a long pair of ears. And that makes a rabbit appear!“ “Yeah, I see the rabbit! So I guess Dad and I were both right!“ “That's why it's an optical illusion. It's a picture that can show different things.“ “Oh.“ “At first, you see only one, but when you keep looking, you can see the other. What animal do you see here?“ “Mm, a cat!“ “This time, I agree with Sid! That is definitely a cat!“ “Are you both sure? Because you're both wrong. See, if I push in closer, I can change your cat . . . into a little white mouse!“ “Incredible! Hey, Mom, do you have any more optical illusions?“ “Yes, look at this straw sticking out of this glass of water.“ “Yeah, that's funny, the straw looks crooked.“ “Sid, I think you’re right. That straw looks crooked to my eyes too.“ “But if you take the straw out, you can see that it's straight. The water causes the illusion.“ “Wow, I know all about optical illusions now. I'm gonna go check on something. Thanks!“ “You're welcome.“ Have you ever looked up in the sky at night and seen the man in the moon? Oh, there he is now! I see a face with two eyes, a nose, and a big smiling mouth. But now that I know all about optical illusions, I know there's no man in the moon. Hey, scientists, you know what you can do is look for your own optical illusions! I'm sure you're surrounded by tons of 'em. It's time for me to go to bed! So remember to keep on discovering!",
                "https://d3iq9oupxk0b1m.cloudfront.net/Manuscript_02480_SidtheScienceKidOpticalIllusions/1x/previewImage.1Crpby.jpg",
                "https://learnwithhomer.com/"
            ),
            Book(
                "Ants!",
                "Hey, you’re a scientist, like me! Come discover with Sid the Science Kid. “Okay, scientists! Today, let’s learn about something really cool . . . ants!” “Yaaaaay!” We’re gonna go find some ants! “Hey, look! Here’s a whole bunch of little ants, and they’re walking in a tiny little line! Ooh!” Hey, you gotta check this out! The ants always walk in a line. Can you see? “That’s because the ants all live together. “Woah.” They do all kinds of things as a group, which is called a colony.” “I’ll bet when the ants work together, they get more work done.” \u2028“Yeah!” “Oh. That’s true, but even when ants are alone, they’re really strong. My mom said one ant can lift sixty ants!” “Whoa!” “Hey, come here! Check this out!” “Huh? What is it?” “Okay!” “Look what these ants are doing! “Ooh!” They’re carrying so much food back to their home! They must be really hungry!” “Scientists, time for class!” “Okay, we’re coming!” “Can we learn more about ants?” “We sure can, Sid! Well, as we learned earlier, ants live together in a group called a colony. They all dig a house underground, which we call an anthill. Outside the anthill, the ants are busy working since every ant has a special job. They also have an interesting way of communicating with each other: they touch antennae.” “I’m an ant!” “Yeah!” “Hi, Gabriela the ant. How are you?” “Oh, it looks like my ant scientists have a lot to talk about!” “I got lots of yummy food, so we should carry it back to the anthill together.” “Okay! Let’s go!” “Now let’s look at all the different jobs ants have. The ‘soldier’ ants stand guard. The ‘worker’ ants collect food, and at bottom of the anthill, lives the queen. She’s busy laying eggs that hatch into baby ants.” “Let’s be ants. I’m the queen! You’re a baby.” “Okay!” “Is someone trying to come in here? I hope they don’t try to take our food away.” “Nope, I’m a soldier ant!” “I’m glad my soldier ants are keeping watch!” “Here I come. I’m a worker ant. I’m bringing food to this little baby so it’ll grow up to be a nice strong ant.” “Oh, eggs? I—I don’t like eggs. I want chicken. Gagoo!” “What a picky baby ant!” “And that’s our anthill!” “Uh-huh. Thank you!” “Wow, I’d love to live in your anthill!” “Hey! An enemy is here! Guys look out!” “Protect the queen!” “Hide the food!” “Go away!” “Oh no, there are so many ants! Oh no! I need to fly away!” “Yay!” “Go away! Get away from our anthill!” “Look at these ants, Sid! I wonder where they’re going.” “They must be worker ants bringing food home to the anthill.” You can observe ants outdoors too. “Hmm, I wonder what else I can find—ooh, a giant insect! What is it?” “It’s Dad bug! Come on in for dinner, guys.” “Bye!”",
                "https://d3iq9oupxk0b1m.cloudfront.net/Manuscript_02531_SidtheScienceKidAnts/1x/previewImage.2LC21k.jpg",
                "https://learnwithhomer.com/"
            )
        )
        val scienceAdapter = ScienceAdapter(data, this)
        binding.recycerScience.adapter = scienceAdapter
        binding.recycerScience.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    // send data to ShowDataActivity
    override fun onItemClicked(book: Book) {
        val intent = Intent(activity, ShowDataActivity::class.java)
        intent.putExtra(KEY_SEND_DATA_SHOW, book)
        startActivity(intent)
    }

}