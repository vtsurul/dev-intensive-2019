package ru.skillbranch.devintensive.models

import android.util.Log

class Bender (var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String  = when (question) {

        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>> {

        val (isValid, message) = question.validateAnswer(answer)

        return if (isValid)
        {
            if(question.answers.contains(answer.toLowerCase()))
            {
                question = question.nextQuestion()
                "Отлично - это правильный ответ!\n${question.question}" to status.color
            }
            else
            {
                if (question == Question.IDLE)

                    "\n${question.question}" to status.color

                else {
                    status = status.nextStatus()

                    if (status == Status.NORMAL)
                        "Это не правильный ответ. Давай все по новой\n${question.question}" to status.color
                    else
                        "Это не правильный ответ!\n${question.question}" to status.color
                }
            }
        }
        else
        {
            "$message\n${question.question}" to status.color
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {

        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if(this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {

        NAME("Как меня зовут?", listOf("бендер", "bender")) {

            override fun nextQuestion(): Question = PROFESSION

            override fun validateAnswer(answer: String): Pair<Boolean, String> {

                if (answer == answer.capitalize())
                    return true to ""
                return false to "Имя должно начинаться с заглавной буквы"
            }
        },

        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {

            override fun nextQuestion(): Question = MATERIAL

            override fun validateAnswer(answer: String): Pair<Boolean, String> {

                if (answer.substring(0,1) == answer.substring(0,1).toLowerCase())
                    return true to ""
                return false to "Профессия должна начинаться со строчной буквы"
            }

        },

        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {

            override fun nextQuestion(): Question = BDAY

            override fun validateAnswer(answer: String): Pair<Boolean, String> {

                if (answer.contains(Regex("[0-9]")))
                    return false to "Материал не должен содержать цифр"
                else
                    return true to ""
            }

        },

        BDAY("Когда меня создали?", listOf("2993")) {

            override fun nextQuestion(): Question = SERIAL

            override fun validateAnswer(answer: String): Pair<Boolean, String> {

                if (answer.contains(Regex("[^0-9]")))
                    return false to "Год моего рождения должен содержать только цифры"
                else
                    return true to ""

            }

        },

        SERIAL("Мой серийный номер?", listOf("2716057")) {

            override fun nextQuestion(): Question = IDLE

            override fun validateAnswer(answer: String): Pair<Boolean, String> {

                if (answer.contains(Regex("[^0-9]")))
                    return false to "Серийный номер содержит только цифры, и их 7"

                else if (answer.length != 7)
                    return false to "Серийный номер содержит только цифры, и их 7"

                else
                    return true to ""
            }

        },

        IDLE("На этом все, вопросов больше нет", listOf()) {

            override fun nextQuestion(): Question = IDLE

            override fun validateAnswer(answer: String): Pair<Boolean, String> {
                return true to ""
            }

        };

        abstract fun nextQuestion(): Question

        abstract fun validateAnswer(answer: String): Pair<Boolean, String>
    }
}