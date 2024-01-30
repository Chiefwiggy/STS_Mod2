package dkSTS.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import dkSTS.DefaultMod;
import dkSTS.characters.TheBruxa;
import dkSTS.events.MysteriousCaller;
import dkSTS.patches.BruxaMirrorChoice;

public class MirrorRoom extends AbstractRoom {

    private final EventRoom fakeRoom;

    public MirrorRoom() {
        this.phase = RoomPhase.EVENT;
        this.mapSymbol = "*";
        this.mapImg = ImageMaster.loadImage(DefaultMod.makeUIPath("map/mirror.png"));
        this.mapImgOutline = ImageMaster.loadImage(DefaultMod.makeUIPath("map/mirror_outline.png"));

        fakeRoom = new EventRoom();
    }

    @Override
    public void onPlayerEntry() {
        AbstractDungeon.overlayMenu.proceedButton.hide();
        this.event = fakeRoom.event = new MysteriousCaller();
        fakeRoom.event.onEnterRoom();
        ((TheBruxa) AbstractDungeon.player).hasTriggeredMirrorEvent = true;
    }

    @Override
    public void update() {
        fakeRoom.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        fakeRoom.render(sb);
        fakeRoom.renderEventTexts(sb);
    }

    @Override
    public void renderAboveTopPanel(SpriteBatch sb) {
        fakeRoom.renderAboveTopPanel(sb);
    }
}
