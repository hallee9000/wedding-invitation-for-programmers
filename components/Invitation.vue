
    <div class="wedding-invitation" :class="{ 'invitation-bounce':isFinished }">
      <div class="invitation-container" :class="{ 'invitation-down':isOpening }">
        <div class="invitation-cover">
          <div class="cover-content" :class="{'invitation-up':isOpening}">
            <div class="content-inside">
              <img class="content-inside-photo" src="/static/image/a/wedding/photo.jpg">
              <p>我们结婚啦！</p>
              <p><b>Jun & undefined</b></p>
              <p>时间：invalid date value</p>
              <p>地点：<b>location can not be found</b></p>
              <input class="content-inside-input" placeholder="轻触写下祝福，按回车发送"  @keyup.enter="getBarrage" v-model="wish" ref="wishInput">
            </div>
          </div>
          <div class="cover-inside-left" :class="{'opening':isOpening}"></div>
          <div class="cover-inside-right" :class="{'opening':isOpening}"></div>
          <img class="cover-inside-seal" src="/static/image/a/wedding/seal.png" @click="openInvitation" :class="{'invitation-flight':isOpening}">
        </div>
      </div>
    </div>