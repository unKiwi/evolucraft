package com.gringo.evolucraft.mixin;

import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    private static final List<String> MOTS_CLES_ACHAT = Arrays.asList("acheter", "vente", "vendre", "achat");

    @Inject(method = "logChatMessage", at = @At("HEAD"))
    protected void logChatMessage(Text message, @Nullable MessageIndicator indicator, CallbackInfo info) {
        String messageText = message.getString().toLowerCase();

        // Vérifier si le message contient des mots-clés indiquant une intention d'achat
        if (contientMotCleAchat(messageText)) {
            // Marquer le message comme une demande d'achat
            System.out.println("Demande d'achat détectée: " + messageText);
        }
    }

    private boolean contientMotCleAchat(String messageText) {
        for (String motCle : MOTS_CLES_ACHAT) {
            if (messageText.contains(motCle)) {
                return true;
            }
        }
        return false;
    }
}
