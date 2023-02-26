package com.lacolinares.androidchatgpt.utils

import com.lacolinares.androidchatgpt.BuildConfig

object AppConstants {

    const val BASE_URL = "https://api.openai.com/"

    const val APPLICATION_TYPE = "application/json"
    const val AUTHORIZATION = "Bearer ${BuildConfig.OPEN_AI_AUTHORIZATION_KEY}"

    /**
     * The model which will generate the completion.
     * Some models are suitable for natural language tasks, others specialize in code.
     */
    const val MODEL = "text-davinci-003"

    /**
     * Controls randomness: Lowering results in less random completions.
     * As the temperature approaches zero, the model will become deterministic and repetitive.
     */
    const val TEMPERATURE = 0

    /**
     * The maximum number of tokens to generate.
     * Requests can use up to 2,048 or 4,000 tokens shared between prompt and completion.
     * The exact limit varies by model. (One token is roughly 4 characters for normal English text)
     */
    const val MAX_TOKENS = 4000

    /**
     * Controls diversity via nucleus sampling: 0.5 means half of all likelihood-weighted options are considered.
     */
    const val TOP_P = 1

    /**
     * How much to penalize new tokens based on their existing frequency in the text so far.
     * Decreases the model's likelihood to repeat the same line verbatim.
     */
    const val FREQUENCY_PENALTY = 0.0

    /**
     * How much to penalize new tokens based on whether they appear in the text so far.
     * Increases the model's likelihood to talk about new topics.
     */
    const val PRESENCE_PENALTY = 0.0

    const val DEFAULT_AI_MESSAGE = "Sorry I didn't get that, please try again."

    object IDGenerator{
        fun getUniqueKey(): String{
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
            return (1..10)
                .map { allowedChars.random() }
                .joinToString("")
        }
    }

    object Urls{
        const val LINK_OPEN_AI = "https://platform.openai.com/examples"
        const val LINK_BUILD_CHAT = "https://www.youtube.com/watch?v=OTK_M_vtbO0&t=749s"
        const val LINK_SOURCE_CODE = "https://github.com/la-colinares/AndroidChatGPT"
        const val LINK_DEVELOPER_WEBSITE = "https://la-colinares.github.io/portfolio/"
    }
}