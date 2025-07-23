import os
import random
from xml.etree.ElementTree import Element, SubElement, ElementTree

CATEGORIES = [
    ("medical", 1),
    ("it", 2),
    ("sport", 3),
    ("history", 4),
]
QUIZZES_PER_CATEGORY = 10
QUESTIONS_RANGE = (20, 30)
ANSWERS_RANGE = (4, 7)  # 1 correct, rest incorrect

# Example question/answer banks for each category
QUESTION_BANK = {
    "medical": [
        ("What is the normal human body temperature?", "37°C", ["40°C", "32°C", "25°C", "45°C", "39°C"]),
        ("What is the main function of red blood cells?", "Transport oxygen", ["Fight infections", "Clot blood", "Produce hormones", "Store fat", "Regulate temperature"]),
        ("Which organ is responsible for filtering blood?", "Kidney", ["Liver", "Heart", "Lung", "Stomach", "Pancreas"]),
        ("Which vitamin is produced when exposed to sunlight?", "Vitamin D", ["Vitamin C", "Vitamin B12", "Vitamin A", "Vitamin K", "Vitamin E"]),
        ("What blood type is the universal donor?", "O negative", ["A positive", "B negative", "AB positive", "O positive", "A negative"]),
        ("What is the largest organ in the human body?", "Skin", ["Liver", "Heart", "Lung", "Brain", "Stomach"]),
        ("What is the medical term for high blood pressure?", "Hypertension", ["Hypotension", "Hyperglycemia", "Hypoglycemia", "Arrhythmia", "Anemia"]),
        ("Which part of the cell contains genetic material?", "Nucleus", ["Cytoplasm", "Mitochondria", "Ribosome", "Cell wall", "Golgi apparatus"]),
        ("What is the main function of white blood cells?", "Fight infections", ["Transport oxygen", "Clot blood", "Store fat", "Produce insulin", "Regulate temperature"]),
        ("Which mineral is important for healthy bones?", "Calcium", ["Iron", "Potassium", "Sodium", "Magnesium", "Zinc"]),
        ("What is the medical term for a heart attack?", "Myocardial infarction", ["Stroke", "Arrhythmia", "Hypertension", "Aneurysm", "Embolism"]),
        ("Which organ produces insulin?", "Pancreas", ["Liver", "Kidney", "Stomach", "Gallbladder", "Spleen"]),
        ("What is the largest artery in the body?", "Aorta", ["Vena cava", "Pulmonary artery", "Femoral artery", "Carotid artery", "Subclavian artery"]),
        ("What is the normal pH of blood?", "7.4", ["6.8", "8.0", "7.0", "7.8", "6.5"]),
        ("Which disease is caused by HIV?", "AIDS", ["Malaria", "Tuberculosis", "Diabetes", "Cancer", "Influenza"]),
        ("What is the main function of platelets?", "Clot blood", ["Transport oxygen", "Fight infections", "Store fat", "Produce hormones", "Regulate temperature"]),
        ("Which vitamin is essential for blood clotting?", "Vitamin K", ["Vitamin D", "Vitamin C", "Vitamin A", "Vitamin B12", "Vitamin E"]),
        ("What is the medical term for low blood sugar?", "Hypoglycemia", ["Hyperglycemia", "Hypertension", "Hypotension", "Anemia", "Arrhythmia"]),
        ("Which organ is affected by hepatitis?", "Liver", ["Kidney", "Heart", "Lung", "Stomach", "Pancreas"]),
        ("What is the main function of the lungs?", "Gas exchange", ["Digest food", "Filter blood", "Produce hormones", "Store fat", "Regulate temperature"]),
        ("What is the medical term for nosebleed?", "Epistaxis", ["Hematuria", "Hemoptysis", "Hematoma", "Edema", "Ascites"]),
        ("Which blood cells help in clotting?", "Platelets", ["Red blood cells", "White blood cells", "Plasma", "Lymphocytes", "Neutrophils"]),
        ("What is the main function of the heart?", "Pump blood", ["Digest food", "Produce hormones", "Store fat", "Regulate temperature", "Filter blood"]),
        ("Which organ is responsible for vision?", "Eye", ["Ear", "Nose", "Tongue", "Skin", "Liver"]),
        ("What is the medical term for inflammation of the liver?", "Hepatitis", ["Nephritis", "Gastritis", "Pancreatitis", "Colitis", "Dermatitis"]),
        ("Which vitamin prevents scurvy?", "Vitamin C", ["Vitamin D", "Vitamin K", "Vitamin A", "Vitamin B12", "Vitamin E"]),
        ("What is the main function of the stomach?", "Digest food", ["Pump blood", "Filter blood", "Produce hormones", "Store fat", "Regulate temperature"]),
        ("Which organ is responsible for hearing?", "Ear", ["Eye", "Nose", "Tongue", "Skin", "Liver"]),
        ("What is the medical term for high blood sugar?", "Hyperglycemia", ["Hypoglycemia", "Hypertension", "Hypotension", "Anemia", "Arrhythmia"]),
        ("Which mineral is important for oxygen transport?", "Iron", ["Calcium", "Potassium", "Sodium", "Magnesium", "Zinc"]),
    ],
    "it": [
        ("What does CPU stand for?", "Central Processing Unit", ["Central Programming Unit", "Computer Personal Unit", "Central Peripheral Unit", "Control Processing Unit", "Central Print Unit"]),
        ("Which language is primarily used for web development?", "JavaScript", ["Python", "C++", "Java", "Swift", "Ruby"]),
        ("What is the main function of RAM?", "Temporary data storage", ["Permanent data storage", "Processing graphics", "Power supply", "Cooling system", "Network connection"]),
        ("Which company developed the Windows OS?", "Microsoft", ["Apple", "Google", "IBM", "Intel", "Dell"]),
        ("What does HTML stand for?", "HyperText Markup Language", ["HighText Machine Language", "HyperTabular Markup Language", "HyperText Markdown Language", "Home Tool Markup Language", "HyperText Main Language"]),
        ("Which protocol is used for secure web browsing?", "HTTPS", ["HTTP", "FTP", "SMTP", "SSH", "SFTP"]),
        ("What is the default port for HTTP?", "80", ["443", "21", "25", "22", "8080"]),
        ("Which device connects multiple networks?", "Router", ["Switch", "Hub", "Repeater", "Bridge", "Modem"]),
        ("What is open source software?", "Software with source code available to the public", ["Software that is free", "Software with no license", "Software that is always paid", "Software that is closed to the public", "Software that is only for Windows"]),
        ("Which of these is a version control system?", "Git", ["Docker", "Jenkins", "Kubernetes", "Linux", "Apache"]),
        ("What does SQL stand for?", "Structured Query Language", ["Simple Query Language", "Sequential Query Language", "Standard Query Language", "Server Query Language", "System Query Language"]),
        ("Which company created the iPhone?", "Apple", ["Samsung", "Google", "Microsoft", "Nokia", "Sony"]),
        ("What is the main function of a firewall?", "Network security", ["Data storage", "Power supply", "Cooling", "Display", "Printing"]),
        ("Which file extension is used for Python files?", ".py", [".js", ".java", ".cpp", ".html", ".exe"]),
        ("What is the main purpose of an operating system?", "Manage hardware and software resources", ["Run games", "Connect to the internet", "Store data", "Print documents", "Edit photos"]),
        ("Which of these is a cloud computing platform?", "AWS", ["Windows", "Linux", "Oracle", "MySQL", "Photoshop"]),
        ("What does GUI stand for?", "Graphical User Interface", ["General User Interface", "Graphical Utility Interface", "Global User Interface", "General Utility Interface", "Graphical Universal Interface"]),
        ("Which of these is a database?", "MySQL", ["React", "Node.js", "Angular", "Vue", "Django"]),
        ("What is the main function of DNS?", "Translate domain names to IP addresses", ["Store emails", "Encrypt data", "Manage files", "Connect printers", "Run applications"]),
        ("Which language is used for Android app development?", "Java", ["Swift", "C#", "Ruby", "PHP", "Go"]),
        ("What is the main function of a compiler?", "Translate code to machine language", ["Run code", "Edit code", "Debug code", "Store code", "Encrypt code"]),
        ("Which of these is a Linux distribution?", "Ubuntu", ["Windows", "macOS", "iOS", "Android", "Solaris"]),
        ("What does API stand for?", "Application Programming Interface", ["Advanced Programming Interface", "Application Program Internet", "Applied Programming Interface", "Automated Programming Interface", "Application Peripheral Interface"]),
        ("Which of these is a markup language?", "XML", ["Python", "C++", "Java", "PHP", "Perl"]),
        ("What is the main function of a browser?", "Access web pages", ["Edit documents", "Store files", "Print photos", "Play music", "Compile code"]),
        ("Which of these is a search engine?", "Google", ["Facebook", "Twitter", "Instagram", "LinkedIn", "YouTube"]),
        ("What does LAN stand for?", "Local Area Network", ["Large Area Network", "Long Area Network", "Light Area Network", "Logical Area Network", "Limited Area Network"]),
        ("Which of these is a programming paradigm?", "Object-oriented", ["Spreadsheet", "Presentation", "Database", "Browser", "Compiler"]),
        ("What is the main function of a modem?", "Modulate and demodulate signals", ["Store data", "Print documents", "Edit photos", "Compile code", "Run games"]),
        ("Which of these is a spreadsheet application?", "Excel", ["Word", "PowerPoint", "Access", "Outlook", "Publisher"]),
    ],
    "sport": [
        ("How many players are there in a soccer team?", "11", ["10", "12", "9", "8", "7"]),
        ("Which country won the FIFA World Cup in 2018?", "France", ["Croatia", "Brazil", "Germany", "Argentina", "Spain"]),
        ("What is the national sport of Japan?", "Sumo wrestling", ["Karate", "Judo", "Baseball", "Soccer", "Tennis"]),
        ("How many rings are there on the Olympic flag?", "5", ["4", "6", "7", "3", "8"]),
        ("Which sport uses a shuttlecock?", "Badminton", ["Tennis", "Squash", "Table tennis", "Volleyball", "Basketball"]),
        ("Who is known as the fastest man in the world?", "Usain Bolt", ["Tyson Gay", "Yohan Blake", "Justin Gatlin", "Asafa Powell", "Carl Lewis"]),
        ("Which country is famous for cricket?", "India", ["USA", "China", "Russia", "Brazil", "Japan"]),
        ("What is the maximum score in a single frame of snooker?", "147", ["155", "100", "120", "130", "140"]),
        ("Which sport is played at Wimbledon?", "Tennis", ["Cricket", "Football", "Golf", "Rugby", "Basketball"]),
        ("How long is a marathon?", "42.195 km", ["40 km", "50 km", "38 km", "45 km", "35 km"]),
        ("Which country hosted the 2016 Summer Olympics?", "Brazil", ["China", "UK", "Russia", "USA", "Japan"]),
        ("What is the highest score in a single game of bowling?", "300", ["200", "250", "350", "400", "150"]),
        ("Which sport uses a puck?", "Ice hockey", ["Field hockey", "Lacrosse", "Basketball", "Baseball", "Tennis"]),
        ("Who has won the most Grand Slam tennis titles?", "Serena Williams", ["Venus Williams", "Maria Sharapova", "Steffi Graf", "Martina Navratilova", "Margaret Court"]),
        ("Which country is known for sumo wrestling?", "Japan", ["China", "Korea", "Thailand", "Vietnam", "India"]),
        ("What is the diameter of a basketball hoop in inches?", "18", ["16", "20", "22", "24", "14"]),
        ("Which sport is known as the 'king of sports'?", "Soccer", ["Basketball", "Tennis", "Cricket", "Baseball", "Rugby"]),
        ("Who is known as the 'King of Football'?", "Pele", ["Maradona", "Messi", "Ronaldo", "Zidane", "Beckham"]),
        ("Which sport uses a bat and ball?", "Baseball", ["Soccer", "Basketball", "Tennis", "Golf", "Rugby"]),
        ("How many holes are there in a standard golf course?", "18", ["9", "12", "15", "21", "24"]),
        ("Which country is famous for rugby?", "New Zealand", ["USA", "India", "China", "Brazil", "Japan"]),
        ("What is the main event in athletics?", "100m sprint", ["Marathon", "Long jump", "High jump", "Shot put", "Discus throw"]),
        ("Which sport is played with a racket?", "Tennis", ["Soccer", "Basketball", "Golf", "Rugby", "Baseball"]),
        ("Who is the most decorated Olympian?", "Michael Phelps", ["Usain Bolt", "Simone Biles", "Larisa Latynina", "Mark Spitz", "Carl Lewis"]),
        ("Which country is known for ice hockey?", "Canada", ["USA", "Russia", "Sweden", "Finland", "Germany"]),
        ("What is the length of an Olympic swimming pool?", "50 meters", ["25 meters", "100 meters", "75 meters", "60 meters", "40 meters"]),
        ("Which sport uses a net?", "Volleyball", ["Soccer", "Basketball", "Tennis", "Golf", "Rugby"]),
        ("Who is the all-time top scorer in the NBA?", "Kareem Abdul-Jabbar", ["Michael Jordan", "LeBron James", "Kobe Bryant", "Shaquille O'Neal", "Wilt Chamberlain"]),
        ("Which country is famous for baseball?", "USA", ["Japan", "Korea", "Cuba", "Dominican Republic", "Venezuela"]),
        ("What is the main event in gymnastics?", "Floor exercise", ["Vault", "Rings", "Pommel horse", "Parallel bars", "Horizontal bar"]),
    ],
    "history": [
        ("Who was the first President of the United States?", "George Washington", ["Thomas Jefferson", "Abraham Lincoln", "John Adams", "James Madison", "Benjamin Franklin"]),
        ("In which year did World War II end?", "1945", ["1939", "1940", "1944", "1946", "1950"]),
        ("Who discovered America?", "Christopher Columbus", ["Amerigo Vespucci", "Leif Erikson", "Ferdinand Magellan", "James Cook", "Marco Polo"]),
        ("What was the name of the ship on which the Pilgrims traveled to America?", "Mayflower", ["Santa Maria", "Endeavour", "Beagle", "Victoria", "Discovery"]),
        ("Who was the first man to walk on the moon?", "Neil Armstrong", ["Buzz Aldrin", "Yuri Gagarin", "Michael Collins", "John Glenn", "Alan Shepard"]),
        ("Which empire was ruled by Julius Caesar?", "Roman Empire", ["Greek Empire", "Ottoman Empire", "British Empire", "Persian Empire", "Mongol Empire"]),
        ("Who wrote the Declaration of Independence?", "Thomas Jefferson", ["George Washington", "John Adams", "Benjamin Franklin", "James Madison", "Alexander Hamilton"]),
        ("What was the name of the first artificial satellite?", "Sputnik", ["Apollo", "Explorer", "Vostok", "Luna", "Pioneer"]),
        ("Who was the British Prime Minister during WWII?", "Winston Churchill", ["Neville Chamberlain", "Clement Attlee", "Margaret Thatcher", "Tony Blair", "David Lloyd George"]),
        ("Which wall fell in 1989?", "Berlin Wall", ["Great Wall of China", "Hadrian's Wall", "Western Wall", "Antonine Wall", "Wailing Wall"]),
        ("Who was the first female Prime Minister of the UK?", "Margaret Thatcher", ["Theresa May", "Angela Merkel", "Indira Gandhi", "Golda Meir", "Benazir Bhutto"]),
        ("Which civilization built the pyramids?", "Egyptians", ["Romans", "Greeks", "Mayans", "Aztecs", "Babylonians"]),
        ("Who was the leader of the Soviet Union during WWII?", "Joseph Stalin", ["Vladimir Lenin", "Nikita Khrushchev", "Leon Trotsky", "Mikhail Gorbachev", "Boris Yeltsin"]),
        ("What was the name of the ship that sank in 1912?", "Titanic", ["Lusitania", "Britannic", "Olympic", "Carpathia", "Empress of Ireland"]),
        ("Who painted the Mona Lisa?", "Leonardo da Vinci", ["Michelangelo", "Raphael", "Donatello", "Vincent van Gogh", "Pablo Picasso"]),
        ("Which war was fought between the North and South regions in the United States?", "Civil War", ["Revolutionary War", "World War I", "World War II", "Vietnam War", "Korean War"]),
        ("Who was the first emperor of China?", "Qin Shi Huang", ["Sun Yat-sen", "Kublai Khan", "Mao Zedong", "Emperor Wu", "Emperor Gaozu"]),
        ("Which city was the capital of the Byzantine Empire?", "Constantinople", ["Athens", "Rome", "Alexandria", "Carthage", "Istanbul"]),
        ("Who invented the telephone?", "Alexander Graham Bell", ["Thomas Edison", "Nikola Tesla", "Guglielmo Marconi", "James Watt", "Samuel Morse"]),
        ("Which country gifted the Statue of Liberty to the USA?", "France", ["England", "Germany", "Italy", "Spain", "Netherlands"]),
        ("Who was the first President of Russia?", "Boris Yeltsin", ["Vladimir Putin", "Mikhail Gorbachev", "Dmitry Medvedev", "Leonid Brezhnev", "Nikita Khrushchev"]),
        ("Which explorer circumnavigated the globe first?", "Ferdinand Magellan", ["Christopher Columbus", "James Cook", "Vasco da Gama", "Marco Polo", "Amerigo Vespucci"]),
        ("Who was the Queen of England during the Spanish Armada?", "Elizabeth I", ["Mary I", "Victoria", "Anne", "Catherine", "Elizabeth II"]),
        ("Which battle ended Napoleon's rule?", "Battle of Waterloo", ["Battle of Trafalgar", "Battle of Leipzig", "Battle of Austerlitz", "Battle of Borodino", "Battle of the Nile"]),
        ("Who was the first black president of South Africa?", "Nelson Mandela", ["Jacob Zuma", "Thabo Mbeki", "Cyril Ramaphosa", "Kgalema Motlanthe", "F.W. de Klerk"]),
        ("Which document ended slavery in the USA?", "Emancipation Proclamation", ["Bill of Rights", "Declaration of Independence", "Constitution", "Gettysburg Address", "Magna Carta"]),
        ("Who was the first man in space?", "Yuri Gagarin", ["Neil Armstrong", "Buzz Aldrin", "John Glenn", "Alan Shepard", "Michael Collins"]),
        ("Which city was divided by a wall from 1961 to 1989?", "Berlin", ["Paris", "London", "Rome", "Vienna", "Prague"]),
        ("Who was the famous nurse during the Crimean War?", "Florence Nightingale", ["Clara Barton", "Mary Seacole", "Edith Cavell", "Elizabeth Blackwell", "Dorothea Dix"]),
        ("Which country was known as Persia?", "Iran", ["Iraq", "Turkey", "Egypt", "Syria", "Greece"]),
    ],
}

def get_question_bank(category):
    return QUESTION_BANK[category]

def generate_quiz_xml(category, quiz_id, quiz_name, description, questions, out_path):
    quiz_el = Element("quiz")
    SubElement(quiz_el, "id").text = str(quiz_id)
    SubElement(quiz_el, "name").text = quiz_name
    SubElement(quiz_el, "description").text = description
    questions_el = SubElement(quiz_el, "questions")
    for q_idx, (q_text, correct, wrongs) in enumerate(questions, 1):
        question_el = SubElement(questions_el, "question")
        SubElement(question_el, "id").text = str(q_idx)
        SubElement(question_el, "question").text = q_text
        SubElement(question_el, "enabled").text = "true"
        answers_el = SubElement(question_el, "answers")
        # Pick 3-6 wrong answers
        n_wrong = random.randint(3, min(6, len(wrongs)))
        wrong_choices = random.sample(wrongs, n_wrong)
        all_answers = [(correct, True)] + [(w, False) for w in wrong_choices]
        random.shuffle(all_answers)
        for a_idx, (a_text, is_correct) in enumerate(all_answers, 1):
            answer_el = SubElement(answers_el, "answer")
            SubElement(answer_el, "id").text = str((q_idx-1)*10 + a_idx)
            SubElement(answer_el, "text").text = a_text
            SubElement(answer_el, "isCorrect").text = str(is_correct).lower()
    tree = ElementTree(quiz_el)
    tree.write(out_path, encoding="utf-8", xml_declaration=True)

if __name__ == "__main__":
    for cat, cat_id in CATEGORIES:
        qbank = get_question_bank(cat)
        for quiz_num in range(1, QUIZZES_PER_CATEGORY+1):
            n_questions = random.randint(*QUESTIONS_RANGE)
            # Ensure uniqueness by shuffling and slicing
            questions = random.sample(qbank, min(n_questions, len(qbank)))
            quiz_name = f"{cat.capitalize()} Quiz {quiz_num}"
            description = f"Test your {cat} knowledge! Quiz {quiz_num}."
            out_file = f"{cat}-quiz-{quiz_num}.xml"
            generate_quiz_xml(cat, (cat_id-1)*10+quiz_num, quiz_name, description, questions, out_file)
    print("Quiz XML files generated.")
