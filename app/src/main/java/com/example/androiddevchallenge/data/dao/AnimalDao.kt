package com.example.androiddevchallenge.data.dao

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Animal
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class AnimalDao {
    private var cache: List<Animal> = (MALE_DOGS + FEMALE_DOGS + MALE_CATS + FEMALE_CATS).also {
        println("here")
    }.shuffled()

    fun getAll() = flowOf(cache)
    fun getByType(type: Animal.Type) = flowOf(cache.filter { it.type == type })

    fun getById(id: Long) = flowOf(cache.find { it.id == id })

    fun toggleFavorite(id: Long) = flow {
        cache = cache.map {
            if (it.id == id) {
                it.copy(favorite = !it.favorite)
            } else it
        }
        emit(cache.find { it.id == id })
    }

    companion object {
        private val MALE_DOGS = listOf(
            Animal(
                0,
                Animal.Type.DOG,
                "Max",
                R.mipmap.airedale_terrier,
                7,
                "Airedale Terrier",
                Animal.Gender.MALE,
                "The Airedale Terrier is the largest of all terrier breeds. Males stand about 23 inches at the shoulder, females a little less. The dense, wiry coat is tan with black markings. Long, muscular legs give Airedales a regal lift in their bearing, and the long head—with its sporty beard and mustache, dark eyes, and neatly folded ears—conveys a keen intelligence. Airedales are the very picture of an alert and willing terrier—only bigger. And, like his smaller cousins in the terrier family, he can be bold, determined, and stubborn. Airedales are docile and patient with kids but won’t back down when protecting hearth and home. Thanks to their famous do-it-all attitude, Airedales excel in all kinds of sports and family activities."
            ),
            Animal(
                1,
                Animal.Type.DOG,
                "Charlie",
                R.mipmap.akita,
                6,
                "Akita",
                Animal.Gender.MALE,
                "Akitas are quiet, fastidious dogs. Wary of strangers and often intolerant of other animals, Akitas will gladly share their silly, affectionate side with family and friends. They thrive on human companionship. The large, independent-thinking Akita is hardwired for protecting those they love. They must be well socialized from birth with people and other dogs."
            ),
            Animal(
                2,
                Animal.Type.DOG,
                "Buddy",
                R.mipmap.welsh_corgi,
                6,
                "Cardigan Welsh Corgi",
                Animal.Gender.MALE,
                "Long, low-set dogs with sturdy bone, short legs, and a deep chest, Cardigans are powerful workers of deceptive speed and grace. Cardis can weigh anywhere from 25 to 34 pounds, with females at the lower end of the scale. They come in several coat colors, from red to the popular blue-merle pattern. The quickest way to distinguish Cardis from their cousins, Pembroke Welsh Corgis, is to check out the hindquarters: Cardigans have tails; Pembrokes do not."
            ),
            Animal(
                3,
                Animal.Type.DOG,
                "Cooper",
                R.mipmap.drever,
                5,
                "Drever",
                Animal.Gender.MALE,
                "The Drever was developed in the early twentieth century in Sweden. Hunting deer was difficult due to terrain and herd locations so hunters soon realized the benefits of using this short-legged, long-bodied dog to drive the deer over long distances and rough terrain right to them. A keen and even-tempered hound, the Drever is never aggressive, nervous or shy. They are content in most living situations, but tend to be vocal when alerting or at play."
            ),
            Animal(
                4,
                Animal.Type.DOG,
                "Rocky",
                R.mipmap.eurasier,
                7,
                "Eurasier",
                Animal.Gender.MALE,
                "The Eurasier comes in a beautiful array of colors. All coat colors are permitted as per the breed standard except for liver color, pure white and irregular white patches. Eurasiers can have purple tongues, pink tongues or spotted tongues. They can also have dark face masks or light, so-called reverse masks. Eurasiers are calm, even-tempered, gentle, loving, intelligent and confident."
            ),
            Animal(
                5,
                Animal.Type.DOG,
                "Jack",
                R.mipmap.finnish_lapphund,
                5,
                "Finnish Lapphund",
                Animal.Gender.MALE,
                "Lappies are friendly and submissive companions, though a bit wary of strangers. They crave companionship and will be miserable when neglected. A distinctive breed trait is a strong “startle reflex,” the result of centuries spent ducking the antlers of ornery reindeer. Despite their propensity for shedding and barking, Lappies are popular pets in their homeland."
            ),
            Animal(
                6,
                Animal.Type.DOG,
                "Jake",
                R.mipmap.french_bulldog,
                9,
                "French Bulldog",
                Animal.Gender.MALE,
                "The bright, affectionate Frenchie is a charmer. Dogs of few words, Frenchies don’t bark much—but their alertness makes them excellent watchdogs. They happily adapt to life with singles, couples, or families, and do not require a lot of outdoor exercise. They get on well with other animals and enjoy making new friends of the human variety. It is no wonder that city folk from Paris to Peoria swear by this vastly amusing and companionable breed."
            ),
            Animal(
                7,
                Animal.Type.DOG,
                "Toby",
                R.mipmap.german_spitz,
                10,
                "German Spitz",
                Animal.Gender.MALE,
                "Spitz breeds like the German Spitz are captivating on account of their beautiful coats, made to stand off by a plentiful undercoat. Particularly impressive is his strong, mane-like collar around his neck, called a ruff, and the bushy tail carried boldly over his back. His foxy head, alert eyes, and small, pointed, closely-set ears give the German Spitz his unique cheeky appearance. His coat comes in a variety of colors including white, black, cream, gold, black and tan, sable, and chocolate brown. Though easily trainable, this lively and intelligent breed can also have an independent streak. If properly trained (so as not to be too noisy) and well socialized, the German Spitz will be happy mingling with other people and dogs."
            ),
            Animal(
                8,
                Animal.Type.DOG,
                "Bailey",
                R.mipmap.hamiltonstovare,
                11,
                "Hamiltonstovare",
                Animal.Gender.MALE,
                "Hamiltonstovare are most commonly multi-purpose dogs; they are hunters, show dogs, and pets, all in one regal and versatile little package. As a hound, they follow their nose wherever it goes (and will not return for a good long while), so leashes and fences are a necessity with this breed. Unlike most scent hounds though, the Hamiltonstovare has an extremely high prey drive for both scent and sight. They can make excellent lure coursing dogs. In the home, they are a lazy, low maintenance dog who rarely sheds. Very food motivated, they can be easy to train despite their hound stubbornness, but higher competitive obedience levels are not usually an option."
            ),
            Animal(
                9,
                Animal.Type.DOG,
                "Oliver",
                R.mipmap.havanese,
                10,
                "Havanese",
                Animal.Gender.MALE,
                "Their small but sturdy bodies, adaptable nature, and social skills make Havanese an ideal city dog, but they are content to be anywhere that they can command the attention of admirers young and old alike. Havanese, smart and trainable extroverts with the comic instincts of a born clown, are natural trick dogs. Havanese are also excellent watchdogs and take the job seriously, but will usually keep the barking to a minimum."
            ),
            Animal(
                10,
                Animal.Type.DOG,
                "Bentley",
                R.mipmap.icelandic_sheepdog,
                5,
                "Icelandic Sheepdog",
                Animal.Gender.MALE,
                "They come in several predominant colors, always accompanied by white markings. An endearing trait is the facial expression: friendly, happy, always looking as though there’s no place they’d rather be than with you."
            ),
            Animal(
                11,
                Animal.Type.DOG,
                "Tucker",
                R.mipmap.irish_wolfhound,
                8,
                "Irish Wolfhound",
                Animal.Gender.MALE,
                "The amiable Irish Wolfhound is an immense, muscular hound gracefully built along classic Greyhound lines, capable of great speed at the gallop. A male might stand nearly 3 feet at the shoulder and weigh up to 180 pounds. Females will run smaller but are still a whole lot of hound. The rough, hard coat comes in many colors, including white, gray, brindle, red, black, and fawn."
            ),
            Animal(
                12,
                Animal.Type.DOG,
                "Duke",
                R.mipmap.jagdterrier,
                6,
                "Jagdterrier",
                Animal.Gender.MALE,
                "The Jagdterrier is a versatile hunting dog from Germany. In German, jagdterrier literally means “hunt terrier.” He is particularly suited to hunting under the ground and as a flushing dog and is relatively small, compact, and well-proportioned. His dense coat, either hard and rough or course and smooth, is usually black and tan, but can be dark brown or grayish-black as well. The tan markings are on his eyebrows, muzzle, chest, legs, and at the base of his tail. He could also have small white markings on his chest and toes."
            ),
            Animal(
                13,
                Animal.Type.DOG,
                "Teddy",
                R.mipmap.jindo,
                6,
                "Jindo",
                Animal.Gender.MALE,
                "The Korea Jindo Dog is a well-proportioned, medium-sized dog used for hunting and guarding. With erect ears and a rolled or sickle-shaped tail, it should be a vivid expression of agility, strength, alertness and dignity.The Jindo has a very strong instinct for hunting and is bold, brave, alert and careful, not tempted easily and impetuous. But most of all he is extremely faithful to his master. On the whole he is not fond of other animals, especially males. He also has a good sense of direction. A one-man dog, he readily accepts a new master, but never forgets his attachment towards the former master who raised him from puppyhood. He keeps himself clean and eats sparingly."
            ),
            Animal(
                14,
                Animal.Type.DOG,
                "Cody",
                R.mipmap.karelian_bear_dog,
                9,
                "Karelian Bear Dog",
                Animal.Gender.MALE,
                "The Karelian Bear Dog is a medium-sized spitz with a dense coat. Bred to hunt large, aggressive game by himself, his build reflects his duties. He is a silent hunter, and only barks once the game is stopped or treed. Working with an experienced hunter, he communicates the type of animal he has located by the sound of his bark. Though he can demonstrate self-control around people, his fighting spirit surfaces around other dogs and can be difficult to handle. His spirit easily turns into aggression, as Karelian Bear Dogs love a challenge."
            ),
            Animal(
                15,
                Animal.Type.DOG,
                "Riley",
                R.mipmap.kromfohrlander,
                10,
                "Kromfohrlander",
                Animal.Gender.MALE,
                "The Kromfohrlander was bred to be a companion only, retains very little hunting instinct (despite its terrier heritage), and is often long-lived (17-18 years old). He is good with children and family and tends to be a one-person dog. He is very attached to his owner, will not run away, and is first to alert to strangers."
            ),
            Animal(
                16,
                Animal.Type.DOG,
                "Bear",
                R.mipmap.lagotto_romangnolo,
                11,
                "Lagotto Romangnolo",
                Animal.Gender.MALE,
                "The Lagotto Romagnolo (plural: Lagotti Romagnoli) is known for wooly curls that cover the body head to tail, crowned by a lavish beard, eyebrows, and whiskers. Lagotti stand under 20 inches and weigh no more than 35 pounds. But don’t be fooled by their teddy-bear looks—these are rugged workers of true strength and endurance. The breed’s trademark curls feel and behave more like human hair than fur."
            ),
            Animal(
                17,
                Animal.Type.DOG,
                "Buster",
                R.mipmap.leonberger,
                4,
                "Leonberger",
                Animal.Gender.MALE,
                "A huge and powerful dog, yes, but the Leonberger is also known for his aristocratic grace and elegance. A male can stand over 31 inches at the shoulder and weigh as much as a full-grown human. Females run smaller but are still a whole lot of dog. Leos require lots of brushing, ample room for romping, and unlimited love."
            ),
            Animal(
                18,
                Animal.Type.DOG,
                "Murphy",
                R.mipmap.mudi,
                5,
                "Mudi",
                Animal.Gender.MALE,
                "Today, the Mudi, though very rare, is seen as an active, intelligent, biddable working breed. It is estimated there are no more than a few thousand Mudi worldwide, with the greatest numbers being in Hungary, followed by Finland, and then even scarcer throughout Europe, the U.S, and Canada."
            ),
            Animal(
                19,
                Animal.Type.DOG,
                "Harley",
                R.mipmap.mountain_cur,
                6,
                "Mountain Cur",
                Animal.Gender.MALE,
                "The Mountain Cur is a fast, hard hunter that runs track with its head in the air. He can be open, semi-open, or silent on track and has a clear bark that can be heard a long distance. They will circle and drift on a cold track if a hot one is not available until they locate a hot track. They are courageous fighters when required and extremely intelligent, with strong treeing instincts, and can easily be trained to leave unwanted game. They respond best to training with a lot of human contact, and in addition to hunting, make great companions and watch dogs."
            ),
        )
        private val FEMALE_DOGS = listOf(
            Animal(
                20,
                Animal.Type.DOG,
                "Bella",
                R.mipmap.nederlandse_kooikerhondje,
                9,
                "Nederlandse Kooikerhondje",
                Animal.Gender.FEMALE,
                "They are instantly recognizable thanks to their large, black-tipped ears and the richly feathered tail they wag proudly. The breed has the sturdy bone structure expected of a serious hunter, but the overall picture is that of a harmoniously built dog of smooth, flowing contours and springy gait."
            ),
            Animal(
                21,
                Animal.Type.DOG,
                "Lucy",
                R.mipmap.norfolk_terrier,
                8,
                "Norfolk Terrier",
                Animal.Gender.FEMALE,
                "Bred to work in packs, Norfolks are more gregarious than a typical terrier, but they have plenty of the old terrier pep. Few Norfolks these days earn their living hunting rodents, but a good one will fearlessly do so when given a chance. Norfolks bond closely, sometimes jealously, with their owners and make nice watchdogs. They have a reputation as a good traveler: portable, adaptable, and up for anything."
            ),
            Animal(
                22,
                Animal.Type.DOG,
                "Daisy",
                R.mipmap.nova_scotia_duck_tolling_retriever,
                5,
                "Nova Scotia Duck Tolling Retriever",
                Animal.Gender.FEMALE,
                "Tollers are upbeat athletes who require outlets for their boundless vigor: hunting, hiking, camping, and, of course, swimming (for which they are ideally suited, down to their webbed feet). Tollers are smart, handsome, affectionate companions, but these red tornadoes can be recommended only to those with enough time and energy to keep them usefully occupied."
            ),
            Animal(
                23,
                Animal.Type.DOG,
                "Molly",
                R.mipmap.ottherhound,
                6,
                "Ottherhound",
                Animal.Gender.FEMALE,
                "These big, bouncy hounds were ideally suited for otter hunting. OHs are built to be expert swimmers, from the top of their rough, waterproof coat to the bottom of their big webbed feet. A broad chest and powerful shoulders allow them to swim all day without tiring. Their large black nose is amazingly sensitive and could follow an otter’s underwater scent trail over great distances. And the OH’s size and strength enabled them to take on a sharp-toothed, razor-clawed otter that might weigh 20 pounds."
            ),
            Animal(
                24,
                Animal.Type.DOG,
                "Maggie",
                R.mipmap.parson_russell_terrier,
                25,
                "Parson Russell Terrier",
                Animal.Gender.FEMALE,
                "PRTs stand 12–15 inches at the shoulder, and weigh 13–17 pounds when in peak condition. Their intelligent expression, mostly white coat, and beautifully balanced body give PRTs the adorable looks of a plush toy come to life. But don’t be fooled by all that cuteness—PRTs are tough little guys built for England’s traditional sport of foxhunting. They’re fast enough to follow the hounds and fearless enough to dig into the ground and flush a fox from his lair."
            ),
            Animal(
                25,
                Animal.Type.DOG,
                "Lola",
                R.mipmap.pembroke_welsh_corgi,
                8,
                "Pembroke Welsh Corgi",
                Animal.Gender.FEMALE,
                "PRTs stand 12–15 inches at the shoulder, and weigh 13–17 pounds when in peak condition. Their intelligent expression, mostly white coat, and beautifully balanced body give PRTs the adorable looks of a plush toy come to life. But don’t be fooled by all that cuteness—PRTs are tough little guys built for England’s traditional sport of foxhunting. They’re fast enough to follow the hounds and fearless enough to dig into the ground and flush a fox from his lair."
            ),
            Animal(
                26,
                Animal.Type.DOG,
                "Sophie",
                R.mipmap.polish_lowland_sheepdog,
                4,
                "Polish Lowland Sheepdog",
                Animal.Gender.FEMALE,
                "First, about that nickname: PON is the acronym for the Polish breed name, Polski Owczarek Nizinny. PONs aren’t particularly large dogs, standing no more than 20 inches at the shoulder, but they are muscular and stocky. The rectangular body is covered head to toe with a double coat—long and shaggy on top, soft and dense below, and it comes in several colors. The head’s profuse coat covers the eyes, which convey the keen, penetrating gaze so common in watchdogs and herders."
            ),
            Animal(
                27,
                Animal.Type.DOG,
                "Chloe",
                R.mipmap.rottweiler,
                4,
                "Rottweiler",
                Animal.Gender.FEMALE,
                "A well-bred and properly raised Rottie will be calm and confident, courageous but not unduly aggressive. The aloof demeanor these world-class guardians present to outsiders belies the playfulness, and downright silliness, that endear Rotties to their loved ones. (No one told the Rottie he’s not a toy breed, so he is liable to plop onto your lap for a cuddle.) Early training and socialization will harness a Rottie’s territorial instincts in a positive way."
            ),
            Animal(
                28,
                Animal.Type.DOG,
                "Sadie",
                R.mipmap.rafeiro_do_alentejo,
                5,
                "Rafeiro Do Alentejo",
                Animal.Gender.FEMALE,
                "Since the beginning of the breed’s history, Rafeiros Alentejanos were used in packs as hunting dogs for big game, though this function has gradually disappeared over time. More recently, the breed is a guard dog for property and livestock, watching sheep and cattle. They also often work alone in prairies and will defend the herd against any intruders. Hailing from Portugal, the Rafeiro do Alentejo is a large-sized dog, powerful, rustic, sober, and calm."
            ),
            Animal(
                29,
                Animal.Type.DOG,
                "Bailey",
                R.mipmap.samoyed,
                5,
                "Samoyed",
                Animal.Gender.FEMALE,
                "Samoyeds, the smiling sledge dogs, were bred for hard work in the world’s coldest locales. In the Siberian town of Oymyakon, for instance, temperatures of minus-60 degrees are common. The Sammy’s famous white coat is thick enough to protect against such brutal conditions. A Sammy sentenced to solitary confinement in the yard is a miserable—and destructive—creature. These are smart, social, mischievous dogs who demand love and attention. Sammies need a very firm but loving hand in training."
            ),
            Animal(
                30,
                Animal.Type.DOG,
                "Coco",
                R.mipmap.schipperke,
                5,
                "Schipperke",
                Animal.Gender.FEMALE,
                "Schipperkes are small dogs built for hard work. Schips were created as ratters and watchdogs. Their powerful jaws, necks, and forequarters—coupled with a stealthy, catlike hunting style—make them ideal rat-catching machines. The black coat is profuse around the neck, shoulders, and legs, giving the breed a silhouette that accentuates a thick, substantial body. The foxy face completes the unique look of a unique breed. If you can’t tell a Schipperke from an ordinary dog, you simply haven’t been paying attention."
            ),
            Animal(
                31,
                Animal.Type.DOG,
                "Gracie",
                R.mipmap.schipperke,
                3,
                "Shetland Sheepdog",
                Animal.Gender.FEMALE,
                "Bright and eager Shelties are easy trainers and world-class competitors in obedience, agility, and herding trials. They are sensitive and affectionate family dogs, highly in tune with the mood of the household. They like to bark and tend to be reserved toward strangers—two qualifications of an excellent watchdog."
            ),
            Animal(
                32,
                Animal.Type.DOG,
                "Roxy",
                R.mipmap.teddy_roosevelt_terrier,
                3,
                "Teddy Roosevelt Terrier",
                Animal.Gender.FEMALE,
                "Teddy Roosevelt Terriers are delightful companions in the home. They learn quickly and are ready for any activity their owners engage in. While they will still keep the family farm and home free of rats and mice, the job for which they were bred, they also excel in companion sports such as agility and obedience."
            ),
            Animal(
                33,
                Animal.Type.DOG,
                "Abby",
                R.mipmap.tornjak,
                4,
                "Tornjak",
                Animal.Gender.FEMALE,
                "The Tornjak is also known as the Bosnian and Herzegovinian – Croatian Shepherd dog. He is a large and powerful dog, well-proportioned and agile. The shape of his body is almost square. When standing and moving, he is strong, harmonious and well-balanced."
            ),
            Animal(
                34,
                Animal.Type.DOG,
                "Zoey",
                R.mipmap.transylvanian_hound,
                5,
                "Transylvanian Hound",
                Animal.Gender.FEMALE,
                "This scent hound is a basic dog, but it can also be used for retrieval. Hunting in a group or alone, you will hear a far-ranging, high, ringing tone indicating the direction of the game. Working far from the farm, they developed problem-solving abilities and through the centuries have become highly intelligent."
            ),
            Animal(
                35,
                Animal.Type.DOG,
                "Stella",
                R.mipmap.vizsla,
                3,
                "Vizsla",
                Animal.Gender.FEMALE,
                "The Vizsla is easily recognized by his sleek golden-rust coat. They can stand between 21 to 24 inches at the shoulder and are the picture of a lean, light-footed hunter’s companion. The long, silky ears frame a facial expression that is sensitive and loving around the house and intense when at work. As a hunter expected to work closely with humans, Vizslas form a tight bond with their owners and hate to be left alone."
            ),
            Animal(
                36,
                Animal.Type.DOG,
                "Zoe",
                R.mipmap.working_kelpie,
                4,
                "Working Kelpie",
                Animal.Gender.FEMALE,
                "The overall appearance of the Working Kelpie is that of a medium-sized, lithe, active, strongly-muscled dog conveying the capability of untiring work. He has been successfully used to manage a variety of stock, including reindeer, goats, cattle and of course sheep. Kelpies in Sweden have gained police dog titles and have pulled sleds. They are also being widely used as search and rescue dogs."
            ),
            Animal(
                37,
                Animal.Type.DOG,
                "Ginger",
                R.mipmap.welsh_springer_spaniel,
                4,
                "Welsh Springer Spaniel",
                Animal.Gender.FEMALE,
                "Welshies have two hallmarks that distinguish them from other spaniels: A uniquely tapered head and an eye-catching coat of bold red and white patterns. The beautiful coat isn’t just ornamental—it’s a waterproof, weatherproof, and thornproof suit that enables Welshies to show off their renowned versatility in all climates and on all terrains."
            ),
            Animal(
                38,
                Animal.Type.DOG,
                "Penny",
                R.mipmap.yakutian_laika,
                3,
                "Yakutian Laika",
                Animal.Gender.FEMALE,
                "The Yakutian Laika is slightly reserved with unknown people but in most cases it’s excited to acquire a new playmate. Human aggressiveness was considered by dogs’ breeders as a major fault and was meticulously eliminated from its characteristics. The breed is endowed with a very sensitive nose and ears, which make it a rather capable watchdog. However its barking is no more than a means to show its anticipation of the perspective of making a new acquaintance. This dog will most likely fail in the role of a guardian due to its friendly nature."
            ),
        )
        private val MALE_CATS = listOf(
            Animal(
                39,
                Animal.Type.CAT,
                "Oliver",
                R.mipmap.abyssinian,
                6,
                "Abyssinian",
                Animal.Gender.MALE,
                "A love of heights is a signal trait of the Abyssinian. He likes to be as high up as possible and will appreciate having one or more ceiling-height cat trees."
            ),
            Animal(
                40,
                Animal.Type.CAT,
                "Max",
                R.mipmap.british_shorthair,
                5,
                "British Shorthair",
                Animal.Gender.MALE,
                "While not overly affectionate, the British Shorthair tends to get along just fine with everyone. They’re mellow and will tolerate other pets, and even though they may not seek out snuggles at every opportunity, they’re happy to be scooped up for a good cuddle."
            ),
            Animal(
                41,
                Animal.Type.CAT,
                "Charlie",
                R.mipmap.california_spangled,
                2,
                "California Spangled",
                Animal.Gender.MALE,
                "The California Spangled is a mixed breed cat–a cross between the Angora, Siamese, American Shorthair, Abyssinian, Manx, and British Shorthair breeds. Energetic, loyal, and loving, these kitties inherited some of the best traits from all of their parent breeds."
            ),
            Animal(
                42,
                Animal.Type.CAT,
                "Jack",
                R.mipmap.korat,
                2,
                "Korat",
                Animal.Gender.MALE,
                "The silver-blue cats with the emerald-green eyes are said to date to the 14th century based on their depiction in ancient literature."
            ),
            Animal(
                43,
                Animal.Type.CAT,
                "Simba",
                R.mipmap.norwegian_forest,
                4,
                "Norwegian Forest",
                Animal.Gender.MALE,
                "The Norwegian Forest Cat is native to Norway, with a history going back hundreds and maybe thousands of years. He figures in fairy tales and legends, one being that the Norse goddess Freya’s chariot is pulled by six giant cats."
            ),
        )
        private val FEMALE_CATS = listOf(
            Animal(
                44,
                Animal.Type.CAT,
                "Luna",
                R.mipmap.desert_lynx,
                4,
                "Desert Lynx",
                Animal.Gender.FEMALE,
                "The Desert Lynx is a mixed breed cat–a cross between a number of other breeds including the American Lynx, Maine Coon, and Pixie Bob, and possibly even the bobcat. These felines are known for being outgoing, playful, and social."
            ),
            Animal(
                45,
                Animal.Type.CAT,
                "Callie",
                R.mipmap.european_shorthair,
                5,
                "European Shorthair",
                Animal.Gender.FEMALE,
                "The European Shorthair is a natural cat breed, which means they developed without the need for human intervention. These felines are known for being friendly, playful, and intelligent."
            ),
            Animal(
                46,
                Animal.Type.CAT,
                "Nala",
                R.mipmap.highlander,
                10,
                "Highlander",
                Animal.Gender.FEMALE,
                "The Highlander is a mixed breed cat–a cross between the Desert Lynx and Jungle Curl breeds. Gentle, social, and intelligent, these felines inherited some of the best traits from their parents."
            ),
            Animal(
                47,
                Animal.Type.CAT,
                "Sophie",
                R.mipmap.laperm,
                2,
                "LaPerm",
                Animal.Gender.FEMALE,
                "The LaPerm’s outstanding feature, of course, is his coat, which has loose, bouncy curls, making it light and airy to the touch."
            ),
            Animal(
                48,
                Animal.Type.CAT,
                "Sophie",
                R.mipmap.laperm,
                2,
                "LaPerm",
                Animal.Gender.FEMALE,
                "The LaPerm’s outstanding feature, of course, is his coat, which has loose, bouncy curls, making it light and airy to the touch."
            ),
            Animal(
                49,
                Animal.Type.CAT,
                "Daisy",
                R.mipmap.ragamuffin,
                4,
                "Ragamuffin",
                Animal.Gender.FEMALE,
                "The Ragamuffin is known for his docile nature. He loves to be held like a baby and will completely relax into your arms."
            ),
        )
    }
}