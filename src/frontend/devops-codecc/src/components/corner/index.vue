<template>
  <div
    :class="['corner', position, `corner-${theme}`]"
    :style="{ width: `${width}px`, height: `${height}px` }"
  >
    <div class="triangle" :style="triangleStyle"></div>
    <template v-if="$slots.content">
      <div class="content">
        <slot name="content"></slot>
      </div>
    </template>
    <template v-else>
      <em>{{ text }}</em>
    </template>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'Corner',
  props: {
    width: {
      type: Number,
      default: 38,
    },
    height: {
      type: Number,
      default: 38,
    },
    text: {
      type: String,
      default: 'new',
    },
    position: {
      type: String,
      default: 'top-left',
      validator(value) {
        if (
          ['top-left', 'top-right', 'bottom-right', 'bottom-left'].indexOf(value) === -1
        ) {
          console.error(`position property is not valid: '${value}'`);
          return false;
        }
        return true;
      },
    },
    theme: {
      type: String,
      default: 'default',
      validator(value) {
        if (
          ['default', 'primary', 'warning', 'success', 'danger'].indexOf(value) < 0
        ) {
          console.error(`theme property is not valid: '${value}'`);
          return false;
        }
        return true;
      },
    },
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(['toolMeta']),
    supportLangs() {
      const toolLang = this.tool.lang;
      const names = this.toolMeta.LANG.map((lang) => {
        if (lang.key & toolLang) {
          return lang.name;
        }
        return false;
      }).filter(name => name);
      return names.join('、');
    },
    triangleStyle() {
      const { width, height, position } = this;
      const style = {
        'top-left': { borderWidth: `${height}px ${width}px 0 0` },
        'top-right': { borderWidth: `0 ${width}px ${height}px 0` },
        'bottom-right': { borderWidth: ` 0 0 ${height}px ${width}px` },
        'bottom-left': { borderWidth: `${height}px 0 0 ${width}px` },
      };

      return style[position];
    },
  },
};
</script>

<style lang="postcss">
@import url('../../css/mixins.css');
$defaultColor: #007bff;
$primaryColor: #699df4;
$warningColor: #ffb848;
$successColor: #45e35f;
$dangerColor: #ea3636;

@define-mixin theme $theme: default, $border-color {
  &.corner-$(theme) {
    .triangle {
      border-color: $border-color;
    }
  }
}

.corner {
  position: absolute;
  width: 46px;
  height: 46px;
  font-size: 12px;
  color: #fff;

  em {
    position: absolute;
    font-size: 12px;
    font-style: normal;
    text-align: center;
  }

  .content {
    position: absolute;
  }

  .triangle {
    position: absolute;
    width: 0;
    height: 0;
    border-style: solid;
  }

  &.top-left {
    top: -1px;
    left: -1px;

    @mixin theme default, $defaultColor transparent transparent transparent;
    @mixin theme primary, $primaryColor transparent transparent transparent;
    @mixin theme warning, $warningColor transparent transparent transparent;
    @mixin theme success, $successColor transparent transparent transparent;
    @mixin theme danger, $dangerColor transparent transparent transparent;

    .triangle {
      border-color: $defaultColor transparent transparent transparent;
      border-width: 1px 1px 0 0;
    }

    .content,
    em {
      top: 4px;
      left: 0;
    }

    em {
      transform: rotate(-45deg) scale(0.9);
    }
  }

  &.top-right {
    top: -1px;
    right: -1px;

    @mixin theme default, transparent $defaultColor transparent transparent;
    @mixin theme primary, transparent $primaryColor transparent transparent;
    @mixin theme warning, transparent $warningColor transparent transparent;
    @mixin theme success, transparent $successColor transparent transparent;
    @mixin theme danger, transparent $dangerColor transparent transparent;

    .triangle {
      border-color: transparent $defaultColor transparent transparent;
      border-width: 0 1px 1px 0;
    }

    .content,
    em {
      top: 4px;
      right: 0;
    }

    em {
      transform: rotate(45deg) scale(0.9);
    }
  }

  &.bottom-right {
    right: -1px;
    bottom: -1px;

    @mixin theme default, transparent transparent $defaultColor transparent;
    @mixin theme primary, transparent transparent $primaryColor transparent;
    @mixin theme warning, transparent transparent $warningColor transparent;
    @mixin theme success, transparent transparent $successColor transparent;
    @mixin theme danger, transparent transparent $dangerColor transparent;

    .triangle {
      border-color: transparent transparent $defaultColor transparent;
      border-width: 0 0 1px 1px;
    }

    .content,
    em {
      right: 0;
      bottom: 4px;
    }

    em {
      transform: rotate(-45deg) scale(0.9);
    }
  }

  &.bottom-left {
    bottom: -1px;
    left: -1px;

    @mixin theme default, transparent transparent transparent $defaultColor;
    @mixin theme primary, transparent transparent transparent $primaryColor;
    @mixin theme warning, transparent transparent transparent $warningColor;
    @mixin theme success, transparent transparent transparent $successColor;
    @mixin theme danger, transparent transparent transparent $dangerColor;

    .triangle {
      border-color: transparent transparent transparent $defaultColor;
      border-width: 1px 0 0 1px;
    }

    .content,
    em {
      bottom: 4px;
      left: 0;
    }

    em {
      transform: rotate(45deg) scale(0.9);
    }
  }
}
</style>
